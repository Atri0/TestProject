package steps;


import pojo.CreateUserResponse;
import pojo.Resource;


import static io.restassured.RestAssured.given;

public class ResourceSteps {
    private CreateUserResponse userResponse;

    public Resource getSingleResource(){
        return given().get("/" + userResponse.getId()).as(Resource.class);
    }
}
