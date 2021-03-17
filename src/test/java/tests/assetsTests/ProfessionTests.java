package tests.assetsTests;

import endpoints.AuthorizationEndpoints;
import endpoints.ProfessionsEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojos.assetsPojo.ProfessionPojo;
import tests.BaseClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class ProfessionTests extends BaseClass {

    private static final String NOT_FOUND_PROFESSION_RESPONSE_MESSAGE = "Profession not found";

//    @BeforeTest
//    void beforeTest() {
//        String token = AuthorizationEndpoints.postAuth_getToken();
//    }

    @Test
    @DisplayName("Being authorized get all professions")
    void given_active_token_check_if_can_get_all_professions_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        ProfessionsEndpoints.get_all_professions(token);
    }

    @Test
    @DisplayName("Being authorized post/add a new profession")
    void given_active_token_check_if_can_add_profession_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        ProfessionsEndpoints.post_profession(token);
    }

    @Test
    @DisplayName("Create a new profession, then get it by passing id and check if created correctly")
    void create_profession_then_get_it_and_check_if_created_properly_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int professionId = ProfessionsEndpoints.post_profession(token).body().jsonPath().getInt("id");
        Response response = ProfessionsEndpoints.get_professionById(token,professionId);
        int createdProfessionId = response.body().jsonPath().getInt("id");

        assertEquals(response.statusCode(), HttpStatus.SC_OK);
        assertEquals(professionId, createdProfessionId);
        assertThat(response.getBody().jsonPath().getString("name"), is(notNullValue()));
        assertThat(response.getBody().jsonPath().getString("authorName"), is(notNullValue()));
    }

    @Test
    @DisplayName("Create a profession, update it and check whether updated")
    void create_profession_then_update_it_and_check_if_updated_properly_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        ProfessionPojo professionPojo = ProfessionsEndpoints.post_profession_return_pojo(token);
        ProfessionPojo updatedProfession = ProfessionsEndpoints.put_professionById(token,professionPojo.getId()).then().extract().body().as(ProfessionPojo.class);
        Response retrieveUpdatedProfession = ProfessionsEndpoints.get_professionById(token,professionPojo.getId());

        assertEquals(retrieveUpdatedProfession.statusCode(), HttpStatus.SC_OK);
        assertThat(updatedProfession.getName(), is(retrieveUpdatedProfession.body().jsonPath().getString("name")));
    }

    @Test
    @DisplayName("Create a profession, delete it and check whether deleted properly")
    void create_profession_then_delete_it_and_check_if_deleted_properly_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int professionId = ProfessionsEndpoints.post_profession(token).then().extract().body().jsonPath().getInt("id");
        ProfessionsEndpoints.get_professionById(token, professionId);
        ProfessionsEndpoints.delete_professionById(token, professionId);
        Response retrieveDeletedProfession = ProfessionsEndpoints.get_professionById(token, professionId);

        assertEquals(retrieveDeletedProfession.statusCode(), HttpStatus.SC_NOT_FOUND);
        assertThat(retrieveDeletedProfession.body().jsonPath().getString("message"), is(NOT_FOUND_PROFESSION_RESPONSE_MESSAGE));
    }
}
