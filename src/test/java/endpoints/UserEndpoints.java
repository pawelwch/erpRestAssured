package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserEndpoints {

    private static final String getUserEndpoint = "/user";

    public static Response getUser(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(getUserEndpoint);
    }
}
