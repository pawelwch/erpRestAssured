package tests.assetsTests;

import com.github.javafaker.Faker;
import endpoints.AuthorizationEndpoints;
import endpoints.FactoryEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.assetsPojo.factoryRequestPojo.City;
import pojos.assetsPojo.factoryRequestPojo.FactoryCommand;
import pojos.assetsPojo.factoryRequestPojo.FactoryPojo;
import tests.BaseClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class FactoryTests extends BaseClass {

    private String token;
    private Faker faker;

    @BeforeMethod
    void beforeTest() {
        token = AuthorizationEndpoints.postAuth_getToken();
        faker = new Faker();
    }

    @Test
    @DisplayName("Being authorized get all factories")
    void given_active_token_check_if_can_get_all_factories_test() {

        Response response = FactoryEndpoints.get_allFactories_WithToken(token);
        int totalFactories = response.jsonPath().getInt("total");
        assertThat(response.getBody().jsonPath().get("totalElements"), is(greaterThan(0)));
        assertThat(response, is(notNullValue()));
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(totalFactories, is(greaterThanOrEqualTo(1)));
    }

    @Test
    @DisplayName("Being authorized post/add a new factory and check if added to the list")
    void given_active_token_check_if_can_add_factory_test() {
        int factoryId = FactoryEndpoints.post_factory(token).then().extract().body().jsonPath().getInt("id");
        Response getAllFactories = FactoryEndpoints.get_allFactories_WithToken(token);

        //todo dodac walidację

    }

    @Test
    @DisplayName("Create a new factory, then get it by passing id and check if created correctly")
    void create_factory_then_get_it_by_passing_id_and_check_if_created_properly_test() {
        int factoryId = FactoryEndpoints.post_factory(token).then().extract().body().jsonPath().getInt("id");
        Response response = FactoryEndpoints.get_factoryById(token, factoryId);

        assertEquals(factoryId, response.body().jsonPath().getInt("id"));
        assertThat(response.getBody().jsonPath().getString("company"), is(notNullValue()));
        assertThat(response.getBody().jsonPath().getString("codeName"), is(notNullValue()));
        /// TODO: 10.05.2021   assertThat(response.getBody().jsonPath().getJsonObject(""), hasProperty("createdAt"));
    }

    @Test
    @DisplayName("Create a factory, update Code Name and check whether updated")
    void create_factory_then_update_Code_Name_and_check_if_updated_properly_test() {
        FactoryPojo factory = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factory);
        factoryCommand.setCodeName(faker.code().asin());
        FactoryPojo updatedFactory = FactoryEndpoints.put_factoryById(token, factory.getId(), factoryCommand).then().extract().as(FactoryPojo.class);

        assertEquals(factoryCommand.getCodeName(), updatedFactory.getCodeName());
    }

    @Test
    @DisplayName("Create a factory, update Company Name and check whether updated")
    void create_factory_then_update_Company_Name_and_check_if_updated_properly_test() {
        FactoryPojo factory = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factory);
        factoryCommand.setCompany(faker.company().name());
        FactoryPojo updatedCompanyName = FactoryEndpoints.put_factoryById(token, factory.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
        FactoryPojo getUpdatedFactory = FactoryEndpoints.get_factoryById(token, updatedCompanyName.getId()).then().extract().as(FactoryPojo.class);

        assertEquals(updatedCompanyName.getCompany(), getUpdatedFactory.getCompany());
        assertEquals(updatedCompanyName.getCompany(), getUpdatedFactory.getCompany());
        assertThat(getUpdatedFactory.getCompany(), is(not(equalTo(factory.getCompany()))));
    }

    @Test
    @DisplayName("Create a factory, update City and check whether updated")
    void create_factory_then_update_City_and_check_if_updated_properly_test() {
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factoryPojo);
        factoryCommand.setCityId(faker.number().numberBetween(1, 100));
        FactoryPojo updatedCity = FactoryEndpoints.put_factoryById(token, factoryPojo.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
        FactoryPojo getUpdatedFactory = FactoryEndpoints.get_factoryById(token, updatedCity.getId()).then().extract().as(FactoryPojo.class);

        assertEquals(updatedCity.getCity().getId(), getUpdatedFactory.getCity().getId());
        assertEquals(updatedCity.getId(), getUpdatedFactory.getId());
        assertThat(getUpdatedFactory.getCity(), is(not(equalTo(factoryPojo.getCompany()))));
    }

    @Test
    @DisplayName("Create a factory, update Post Code and check whether updated")
    void create_factory_then_update_Post_Code_and_check_if_updated_properly_test() {
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factoryPojo);
        factoryCommand.setPostcode(faker.address().zipCode());
        FactoryPojo updatedPostCode = FactoryEndpoints.put_factoryById(token, factoryPojo.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
        FactoryPojo getUpdatedPostCode = FactoryEndpoints.get_factoryById(token, updatedPostCode.getId()).then().extract().as(FactoryPojo.class);

        assertEquals(updatedPostCode.getPostcode(), getUpdatedPostCode.getPostcode());
        assertEquals(updatedPostCode.getId(), getUpdatedPostCode.getId());
        assertThat(factoryPojo.getPostcode(), is(not(equalTo(getUpdatedPostCode))));
    }

    @Test
    @DisplayName("Create a factory, update Street and check whether updated")
    void create_factory_then_update_Street_and_check_if_updated_properly_test() {
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factoryPojo);
        factoryCommand.setStreet(faker.address().streetName());
        FactoryPojo updatedStreet = FactoryEndpoints.put_factoryById(token, factoryPojo.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
        FactoryPojo getUpdatedStreet = FactoryEndpoints.get_factoryById(token, updatedStreet.getId()).then().extract().as(FactoryPojo.class);

        assertEquals(updatedStreet.getStreet(), getUpdatedStreet.getStreet());
        assertEquals(updatedStreet.getId(), getUpdatedStreet.getId());
        assertThat(getUpdatedStreet.getStreet(), is(not(equalTo(factoryPojo.getStreet()))));
    }

    @Test
    @DisplayName("Create a factory, update Street Number and check whether updated")
    void create_factory_then_update_Street_Number_and_check_if_updated_properly_test() {
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factoryPojo);
        factoryCommand.setStreetNumber(faker.address().streetAddressNumber());
        FactoryPojo updatedStreetNumber = FactoryEndpoints.put_factoryById(token, factoryPojo.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
        FactoryPojo getUpdatedStreetNumber = FactoryEndpoints.get_factoryById(token, updatedStreetNumber.getId()).then().extract().as(FactoryPojo.class);

        assertEquals(updatedStreetNumber.getStreetNumber(), getUpdatedStreetNumber.getStreetNumber());
        assertEquals(updatedStreetNumber.getId(), getUpdatedStreetNumber.getId());
        assertThat(getUpdatedStreetNumber.getStreetNumber(), is(not(equalTo(factoryPojo.getStreetNumber()))));
    }

    @Test
    @DisplayName("Create a factory, update House Number and check whether updated")
    void create_factory_then_update_House_Number_and_check_if_updated_properly_test() {
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        FactoryCommand factoryCommand = new FactoryCommand(factoryPojo);
        factoryCommand.setStreetNumber(faker.address().streetAddressNumber());
        FactoryPojo updatedStreetNumber = FactoryEndpoints.put_factoryById(token, factoryPojo.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
        FactoryPojo getUpdatedStreetNumber = FactoryEndpoints.get_factoryById(token, updatedStreetNumber.getId()).then().extract().as(FactoryPojo.class);

        assertEquals(updatedStreetNumber.getStreetNumber(), getUpdatedStreetNumber.getStreetNumber());
        assertEquals(updatedStreetNumber.getId(), getUpdatedStreetNumber.getId());
        assertThat(getUpdatedStreetNumber.getStreetNumber(), is(not(equalTo(factoryPojo.getStreetNumber()))));
    }

    @Test
    @DisplayName("Create factory, delete it and check if deleted properly")
    void create_factory_then_delete_and_check_if_deleted_properly_test() {
        FactoryPojo factory = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
    }

}
