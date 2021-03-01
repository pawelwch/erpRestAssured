package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    private static final String GET_USER_ENDPOINT = "/user";

    public static Response getUser(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_USER_ENDPOINT);
    }
}
