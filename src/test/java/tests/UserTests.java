package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.UsersEndpoints;
import io.restassured.response.Response;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import pojos.authPojo.UserLogin;
import pojos.userPojo.EntireUserProfile;
import pojos.userPojo.UserProfile;
import pojos.userPojo.UserTypes;

import static io.restassured.RestAssured.given;
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
        assertThat(getUserById.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("Create Central role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenCentralRoleIsCreatedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.CENTRAL).then().extract().body().jsonPath().getInt("id");
        Response getUserById = UsersEndpoints.getUserById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "CENTRAL");
        assertThat(getUserById.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("Create Foreman role with random data and check if created properly")
    void givenRandomUserDataWhenPostUserEndpointThenForemanRoleIsCreatedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.FOREMAN).then().extract().body().jsonPath().getInt("id");
        Response getUserById = UsersEndpoints.getUserById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "FOREMAN");
        assertThat(getUserById.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("After creating an Administrator role, get all user data by passing his ID and check if correct data returned")
    void givenCreatedAdministratorIdWhenGetUserEndpointThenUserDataIsReturnedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = UsersEndpoints.postUser(token, UserTypes.ADMINISTRATOR).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = UsersEndpoints.getUserById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));
    }

    @Test
    @DisplayName("After creating an Central role, get all user data by passing his ID and check if correct data returned")
    void givenCreatedCentralIdWhenGetUserEndpointThenUserDataIsReturnedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = UsersEndpoints.postUser(token, UserTypes.CENTRAL).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = UsersEndpoints.getUserById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));
    }

    @Test
    @DisplayName("After creating an Foreman role, get all user data by passing his ID and check if correct data returned")
    void givenCreatedForemanIdWhenGetUserEndpointThenUserDataIsReturnedTest() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = UsersEndpoints.postUser(token, UserTypes.FOREMAN).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = UsersEndpoints.getUserById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));
    }

    @Test
    @DisplayName("After creating an ADMINISTRATOR role, then try to delete him and check if deleted properly")
    void create_Administrator_role_delete_him_check_if_deleted_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.ADMINISTRATOR).then().extract().body().jsonPath().getInt("id");
        Response response = UsersEndpoints.deleteUserById("Tester123!", userId, token);
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response deletedUser = UsersEndpoints.getUserById(token, userId);

        assertThat(deletedUser.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        //todo dopisac dodatkową assercje
    }

    @Test
    @DisplayName("After creating an CENTRAL role, then try to delete him and check if deleted properly")
    void create_Central_role_delete_him_check_if_deleted_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.CENTRAL).then().extract().body().jsonPath().getInt("id");
        Response response = UsersEndpoints.deleteUserById("Tester123!", userId, token);
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response deletedUser = UsersEndpoints.getUserById(token, userId);

        assertThat(deletedUser.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        //todo dopisac dodatkową assercje
    }

    @Test
    @DisplayName("After creating an FOREMAN role, then try to delete him and check if deleted properly")
    void create_Foreman_role_delete_him_check_if_deleted_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = UsersEndpoints.postUser(token, UserTypes.FOREMAN).then().extract().body().jsonPath().getInt("id");
        Response response = UsersEndpoints.deleteUserById("Tester123!", userId, token);
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response deletedUser = UsersEndpoints.getUserById(token, userId);

        assertThat(deletedUser.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        //todo dopisac dodatkową assercje
    }



    @Test
    @DisplayName("After creating an Administrator user role, then try to block him and check if blocked properly")
    void givenCreatedUserIdWhenPutBlockThenUserIsBlockedTest() {
        //1. logowanie
        String token = AuthorizationEndpoints.postAuth_getToken();
        //2. tworzenie Operatora
        int userId = UsersEndpoints.postUser(token, UserTypes.FOREMAN).then().extract().body().jsonPath().getInt("id");
        //3. blockowanie Operatora poprzez podanie jego id i hasła
        //todo dodac metode na weryfikacje userka
        Response response = UsersEndpoints.blockUserById("Tester123!", userId, token);
        //4. assercja czy został zablokowany
        assertThat(response.statusCode(), is(204));
    }

    //todo test na blokowanie usera
    //todo test na aktywacje usera
    //todo test edycji usera




}
