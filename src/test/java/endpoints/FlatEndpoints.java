package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FlatEndpoints {

    private static final String GET_AVAILABLE_FLATS = "/flat/available";


    public static Response get_available_flats(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_AVAILABLE_FLATS);
    }
}
