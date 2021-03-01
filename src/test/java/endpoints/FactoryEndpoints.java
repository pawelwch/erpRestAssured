package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FactoryEndpoints {

    private static final String GET_FACTORY_ENDPOINT = "/factory";

    public static Response getAllFactories_WithToken(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_FACTORY_ENDPOINT)
                .then().extract().response();
    }

    public static Response getAllFactories_WithoutToken() {
        return given()
                .when().get(GET_FACTORY_ENDPOINT)
                .then().extract().response();
    }

}
