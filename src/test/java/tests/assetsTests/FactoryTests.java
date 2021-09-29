package tests.assetsTests;

import com.github.javafaker.Faker;
import endpoints.AuthorizationEndpoints;
import endpoints.FactoryEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojos.assetsPojo.factoryRequestPojo.FactoryPojo;
import tests.BaseClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class FactoryTests extends BaseClass {

    private String token;
    private Faker faker;

    @BeforeEach
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
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        Response getCreatedFactory = FactoryEndpoints.get_factoryById(token, factoryPojo.getId());
        assertEquals(getCreatedFactory.statusCode(), HttpStatus.SC_OK);

        FactoryPojo updateFactory = FactoryEndpoints.put_factoryById(token, getCreatedFactory.getBody().jsonPath().getInt("id")).then().extract().as(FactoryPojo.class);
        Response getUpdatedFactory = FactoryEndpoints.get_factoryById(token, factoryPojo.getId());

        assertEquals(getUpdatedFactory.statusCode(), HttpStatus.SC_OK);
        assertThat(getUpdatedFactory.getBody().jsonPath().getString("codeName"), is(not(factoryPojo.getCodeName())));
    }

    @Test
    @DisplayName("Create a factory, update Company Name and check whether updated")
    void create_factory_then_update_Company_Name_and_check_if_updated_properly_test() {
        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        Response getCreatedFactory = FactoryEndpoints.get_factoryById(token, factoryPojo.getId());
        assertEquals(getCreatedFactory.statusCode(), HttpStatus.SC_OK);

        FactoryPojo updateFactory = FactoryEndpoints.put_factoryById(token, getCreatedFactory.getBody().jsonPath().getInt("id")).then().extract().as(FactoryPojo.class);
        Response getUpdatedFactory = FactoryEndpoints.get_factoryById(token, factoryPojo.getId());

        assertEquals(getUpdatedFactory.statusCode(), HttpStatus.SC_OK);
        assertThat(getUpdatedFactory.getBody().jsonPath().getString("name"), is(not(factoryPojo.getCodeName())));
    }

    //todo po zmianie w api do modyfikacji
//    @Test
//    @DisplayName("Create a factory, update City and check whether updated")
//    void create_factory_then_update_Address_and_check_if_updated_properly_test() {
//        FactoryPojo factoryPojo = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
//        FactoryCommand factoryCommand = new FactoryCommand(factoryPojo);
//        //factoryCommand.setCityId(faker.number().numberBetween(1, 100));
//        FactoryPojo updatedCity = FactoryEndpoints.put_factoryById(token, factoryPojo.getId(), factoryCommand).then().extract().as(FactoryPojo.class);
//        FactoryPojo getUpdatedFactory = FactoryEndpoints.get_factoryById(token, updatedCity.getId()).then().extract().as(FactoryPojo.class);
//
//        assertEquals(updatedCity.getCity().getId(), getUpdatedFactory.getCity().getId());
//        assertEquals(updatedCity.getId(), getUpdatedFactory.getId());
//        assertThat(getUpdatedFactory.getCity(), is(not(equalTo(factoryPojo.getCompany()))));
//    }

    @Test
    @DisplayName("Create factory, delete it and check if deleted properly")
    void create_factory_then_delete_and_check_if_deleted_properly_test() {
        FactoryPojo factory = FactoryEndpoints.post_factory(token).then().extract().as(FactoryPojo.class);
        Response createdFactory = FactoryEndpoints.get_factoryById(token, factory.getId());
        assertThat(createdFactory.getBody().jsonPath().getString("id"), is(notNullValue()));

        Response response = FactoryEndpoints.delete_factoryById(token, createdFactory.then().extract().body().jsonPath().getInt("id"));
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response getDeletedFactory = FactoryEndpoints.get_factoryById(token, factory.getId());
        assertThat(getDeletedFactory.statusCode(), is(HttpStatus.SC_NOT_FOUND));
    }
}
