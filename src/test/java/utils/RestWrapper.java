package utils;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import pojo.UserLogin;

import static io.restassured.RestAssured.given;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api";
    private Cookies cookies;

    public UserService userService;
    public OrderServies orderServies;
    public ResourceServies resourceServies;

    private RestWrapper(Cookies cookies){
        this.cookies=cookies;
        userService=new UserService(cookies);
        orderServies=new OrderServies(cookies);
        resourceServies=new ResourceServies(cookies);
    }

    public static RestWrapper loginAs(String login, String password){
        Cookies cookie=given().contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("/login")
                .body(new UserLogin(login,password))
                .post()
                .getDetailedCookies();
        return new RestWrapper(cookie);
    }
}
