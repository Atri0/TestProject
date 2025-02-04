package utils;

import io.restassured.http.Cookies;
import pojo.UserRequest;
import pojo.CreateUserResponse;
import pojo.Users;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends  RestService{
    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserService(Cookies cookies) {
        super(cookies);
    }

    public CreateUserResponse createUser(UserRequest rq){
        return given().spec(REQUEST_SPECIFICATION).body(rq)
                .post().as(CreateUserResponse.class);
    }

    public List<Users> getUsers(){
        return given().spec(REQUEST_SPECIFICATION)
                .log().all()
                .get().jsonPath()
                .getList("data", Users.class);
    }
}
