package utils;

import io.restassured.http.Cookies;
import pojo.Users;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderServies extends RestService{
    @Override
    protected String getBasePath() {
        return "/orders";
    }

    public OrderServies(Cookies cookies) {
        super(cookies);
    }

    public List<Users> getOrders(){
        return given().spec(REQUEST_SPECIFICATION)
                .log().all()
                .get().jsonPath()
                .getList("data", Users.class);
    }
}
