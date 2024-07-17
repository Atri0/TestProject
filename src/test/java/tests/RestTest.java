package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Resource;
import pojo.UserRequest;
import pojo.CreateUserResponse;
import pojo.Users;
import steps.UserGenerator;
import utils.RestWrapper;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RestTest {
   private static RestWrapper api;

    @BeforeAll
    public static void prepareClient(){
        api=RestWrapper.loginAs("eve.holt@regres.in","cityslicks");
    }

    static Stream<Resource> resourceDataProvider() {
        return Stream.of(
                new Resource(1, "cerulean", 2000, "#98B2D1", "15-4020"),
                new Resource(2, "fuchsia rose", 2001, "#C74375", "17-2031"),
                new Resource(3, "true red", 2002, "#BF1932", "19-1664"),
                new Resource(4, "aqua sky", 2003, "#7BC4C4", "14-4811"),
                new Resource(5, "tigerlily", 2004, "#E2583E", "17-1456"),
                new Resource(6, "blue turquoise", 2005, "#53B0AE", "15-5217")
        );
    }

    @Test
    public void getUsers(){
        assertThat(api.userService.getUsers())
                .extracting(Users::getEmail)
                .contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser(){
        UserRequest request = UserGenerator.getSimpleUser();
        CreateUserResponse response=api.userService.createUser(request);

        assertThat(response)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(request.getName());
    }

    @ParameterizedTest
    @MethodSource("resourceDataProvider")
    public void checkResourceContent(Resource expectedResource) {
        List<Resource> actualResourceList = api.resourceServies.getResource();

        assertThat(actualResourceList.size()).isEqualTo(6);

        Resource actualResource = actualResourceList.stream()
                .filter(resource -> resource.getId() == expectedResource.getId())
                .findFirst()
                .orElse(null);

        assertThat(actualResource).isNotNull();
        assertThat(actualResource.getName()).isEqualTo(expectedResource.getName());
        assertThat(actualResource.getYear()).isEqualTo(expectedResource.getYear());
        assertThat(actualResource.getColor()).isEqualTo(expectedResource.getColor());
        assertThat(actualResource.getPantoneValue()).isEqualTo(expectedResource.getPantoneValue());
    }
}
