package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.assetsPojo.factoryRequestPojo.FactoryCommand;


import static io.restassured.RestAssured.given;

public class FactoryEndpoints {

    private static Faker faker = new Faker();

    private static final String GET_FACTORY_ENDPOINT = "/factory";
    private static final String POST_FACTORY_ENDPOINT = "/factory";
    private static final String GET_FACTORY_BY_ID_ENDPOINT = "/factory/{factoryId}";
    private static final String PUT_FACTORY_BY_ID_ENDPOINT = "/factory/{factoryId}";
    private static final String DELETE_FACTORY_BY_ID_ENDPOINT = "/factory/{factoryId}";


    @DisplayName("Get first all 500 factories")
    public static Response get_allFactories_WithToken(String token) {
        return given().auth().preemptive().oauth2(token).queryParam("size", 500)
                .when().get(GET_FACTORY_ENDPOINT)
                .then().extract().response();
    }

    @DisplayName("Get all factories without authorization")
    public static Response get_allFactories_WithoutToken() {
        return given()
                .when().get(GET_FACTORY_ENDPOINT)
                .then().extract().response();
    }

    @DisplayName("Post new factory with random data")
    public static Response post_factory(String token) {
        FactoryCommand factory = FactoryCommand.builder()
                .company(faker.company().name())
                .codeName(faker.company().name() + faker.number().randomDigit())
                .addressId("16.27278/50.730396")
                .build();

        return given().auth().preemptive().oauth2(token).body(factory)
                .when().post(POST_FACTORY_ENDPOINT);
    }

    @DisplayName("Update specific factory by passing id")
    public static Response put_factoryById(String token, int factoryId) {
        return given().auth().preemptive().oauth2(token)
                .body(new FactoryCommand(faker.company().name(), faker.company().name() + "" + faker.number().randomDigit(),"16.27278/50.730396", faker.number().digit()))
                .pathParam("factoryId", factoryId)
                .when().put(PUT_FACTORY_BY_ID_ENDPOINT);
    }


    @DisplayName("Get specific factory by passing id")
    public static Response get_factoryById(String token, int factoryId) {
        return given().auth().preemptive().oauth2(token).pathParam("factoryId", factoryId)
                .when().get(GET_FACTORY_BY_ID_ENDPOINT);
    }

    @DisplayName("Delete specific factory by passing id")
    public static Response delete_factoryById(String token, int factoryId) {
        return given().auth().preemptive().oauth2(token).pathParam("factoryId", factoryId)
                .when().delete(DELETE_FACTORY_BY_ID_ENDPOINT);
    }


}
