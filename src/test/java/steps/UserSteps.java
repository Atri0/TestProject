package steps;

import pojo.CreateUserResponse;
import pojo.Users;

import static io.restassured.RestAssured.given;

public class UserSteps {
    private CreateUserResponse userResponse;

    public Users getUser(){
        return given().get("/" + userResponse.getId()).as(Users.class);
    }
}