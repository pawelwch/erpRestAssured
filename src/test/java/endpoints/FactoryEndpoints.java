package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FactoryEndpoints {

    private static final String factoryEndpoint = "/factory";

    public static Response getAllFactories_WithToken(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(factoryEndpoint)
                .then().extract().response();
    }

    public static Response getAllFactories_WithoutToken() {
        return given()
                .when().get(factoryEndpoint)
                .then().extract().response();
    }

}
