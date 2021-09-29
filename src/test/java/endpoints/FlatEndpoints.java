package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FlatEndpoints {

    private static final String GET_AVAILABLE_FLATS = "/flat/available";


    public static Response get_available_flats(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_AVAILABLE_FLATS);
    }

    public static int return_first_available_room(String token) {
        Response availableFlats = FlatEndpoints.get_available_flats(token);
        return availableFlats.getBody().jsonPath().getInt("content[0].rooms[0].id");
    }
}
