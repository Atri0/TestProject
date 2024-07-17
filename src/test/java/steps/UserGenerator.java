package steps;

import pojo.UserRequest;

public class UserGenerator {

    public static UserRequest getSimpleUser(){
        UserRequest request =
                UserRequest.builder()
                        .name("simple1")
                        .job("automation2")
                        .build();
        return request;
    }
}
