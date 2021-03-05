package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.UsersEndpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import pojos.authPojo.UserAuth;
import pojos.userPojo.EntireUserProfile;
import pojos.userPojo.User;
import pojos.userPojo.UserTypes;

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
        String token = AuthorizationEndpoints.postAuth_getToken();;
        int userId = UsersEndpoints.postUser(token, UserTypes.ADMINISTRATOR).then().extract().body().jsonPath().getInt("id");
        Response getUserById = UsersEndpoints.getUserById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "ADMINISTRATOR");
        assertThat(getUserById.statusCode(), is(200));
    }

    @Test
    @DisplayName("Create Central role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenCentralRoleIsCreatedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.CENTRAL).then().extract().body().jsonPath().getInt("id");
        Response getUserById = UsersEndpoints.getUserById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "CENTRAL");
        assertThat(getUserById.statusCode(), is(200));
    }

    @Test
    @DisplayName("Create Foreman role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenForemanRoleIsCreatedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.FOREMAN).then().extract().body().jsonPath().getInt("id");
        Response getUserById = UsersEndpoints.getUserById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "FOREMAN");
        assertThat(getUserById.statusCode(), is(200));
    }

    @Test
    @DisplayName("After creating an Administrator, get all user data by passing his ID and check if correct data returned")
    void givenCreatedUserIdWhenGetUserEndpointThenUserDataIsReturnedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = UsersEndpoints.postUser(token, UserTypes.ADMINISTRATOR).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = UsersEndpoints.getUserById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));

    }

    @Test
    @DisplayName("After creating an ADMINISTRATOR user role, then try to delete him and check if deleted properly")
    void givenCreatedUserIdWhenPutDeleteEndpointThenUserIsDeletedTest() {
        UserAuth userAuth = AuthorizationEndpoints.postAuth_userAuthorized().then().extract().body().as(UserAuth.class);
        int userId = UsersEndpoints.postUser(userAuth.getAccessToken(), UserTypes.ADMINISTRATOR).then().extract().body().jsonPath().getInt("id");
        Response response = UsersEndpoints.deleteUserById(userAuth.getPassword(),userId);
        //todo dokonczyc
    }


    //todo test na usuwanie usera
    //todo test na blokowanie usera
    //todo test na aktywacje usera
    //todo test edycji usera




}
