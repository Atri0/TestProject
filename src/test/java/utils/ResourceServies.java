package utils;

import io.restassured.http.Cookies;
import pojo.Resource;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ResourceServies extends RestService{

    public ResourceServies(Cookies cookies) {
        super(cookies);
    }

    @Override
    protected String getBasePath() {
        return "unknown";
    }

    public List<Resource> getResource(){
        return given().spec(REQUEST_SPECIFICATION)
                .log().all()
                .get().then().statusCode(200)
                .extract().jsonPath()
                .getList("data", Resource.class);
    }
}
