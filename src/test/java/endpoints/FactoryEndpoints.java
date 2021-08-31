package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.assetsPojo.factoryRequestPojo.City;
import pojos.assetsPojo.factoryRequestPojo.FactoryCommand;
import pojos.assetsPojo.factoryRequestPojo.FactoryPojo;
import pojos.assetsPojo.professionRequestPojo.ProfessionCommand;

import java.util.Date;

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
        FactoryCommand factory = new FactoryCommand();

        factory.setCompany(faker.company().name());
        factory.setCodeName(faker.company().name() + " " + faker.number().randomDigit());
        factory.setStreet(faker.address().streetAddress());
        factory.setPostcode(faker.address().zipCode());
        factory.setStreetNumber(faker.address().streetAddressNumber());
        factory.setHouseNumber(faker.address().buildingNumber());
        factory.setCityId(faker.number().numberBetween(1, 20));

        return given().auth().preemptive().oauth2(token).body(factory)
                .when().post(POST_FACTORY_ENDPOINT);
    }

    @DisplayName("Update specific factory by passing id")
    public static Response put_factoryById(String token, int factoryId, FactoryCommand factoryCommand) {
        return given().auth().preemptive().oauth2(token).body(factoryCommand)
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

//    public static FactoryPojo buildUpdatedFactory() {
//        return FactoryPojo.builder()
//                .company(faker.company().name() + "updated")
//                .codeName(faker.name().suffix() + faker.number() + "updated")
//                .street(faker.address().streetAddress() + "updated")
//                .city(City.builder().id(12).build())
//                .postcode("12-123")
//                .streetNumber(faker.address().streetAddressNumber() + "updated")
//                .houseNumber(faker.address().buildingNumber() + "updated")
//                .build();
//    }
//
//    public static FactoryPojo buildCompanyUpdate() {
//        return FactoryPojo.builder()
//                .company(faker.company().name() + "updated")
//                .build();
//    }


}
