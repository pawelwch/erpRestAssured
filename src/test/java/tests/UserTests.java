package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.UsersEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import pojos.userPojo.User;
import pojos.userPojo.UserTypes;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserTests extends BaseClass{

    @Test
    @DisplayName("After creating a user, get all his data")
    void givenLoggedInUserDataWhenGetUserDetailsThenAllUserDataIsReturnedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");
        Response getUserResponse = UsersEndpoints.getUserData(token);

        assertEquals(getUserResponse.statusCode(), HttpStatus.SC_OK);
        assertThat(getUserResponse.body().jsonPath().getString("id"), notNullValue());
        assertThat(getUserResponse.body().jsonPath().getString("email"), notNullValue());
    }

    @Test
    @DisplayName("Create Administrator role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenAdministratorRoleIsCreatedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");

        Response postUserResp = UsersEndpoints.postUser(token, UserTypes.ADMINISTRATOR);
        assertThat(postUserResp.statusCode(), is(201));
        // todo dodać dodatkową assercje na response
    }

    @Test
    @DisplayName("Create Central role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenCentralRoleIsCreatedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");

        Response postUserResp = UsersEndpoints.postUser(token, UserTypes.CENTRAL);
        assertThat(postUserResp.statusCode(), is(201));
        // todo dodać dodatkową assercje na response
    }

    @Test
    @DisplayName("Create Foreman role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenForemanRoleIsCreatedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");

        Response postUserResp = UsersEndpoints.postUser(token, UserTypes.FOREMAN);
        assertThat(postUserResp.statusCode(), is(201));
        // todo dodać dodatkową assercje na response
    }


    @Test
    @DisplayName("Get all user data by passing his ID and check if correct data returned")
    void givenCreatedUserIdWhenGetUserEndpointThenUserDataIsReturnedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");
        Response postUserResp = UsersEndpoints.getUserById(token, 22);

        // todo sprawdzic czy zwarcany id sie zgadza
    }



}
