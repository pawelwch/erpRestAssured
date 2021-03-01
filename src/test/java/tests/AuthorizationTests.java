package tests;

import endpoints.AuthorizationEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.*;

public class AuthorizationTests extends BaseClass{

    private static final String RESPONSE_MESSAGE_BAD_PASSWORD = "BAD_PASSWORD";
    private static final String RESPONSE_MESSAGE_USER_NOT_FOUND = "User not found";
    private static final String RESPONSE_MESSAGE_MUST_BE_NOT_NULL = "must not be null";
    private static final String RESPONSE_MESSAGE_TOO_MANY_LOGIN_ATTEMPTS = "TOO_MANY_LOGIN_ATTEMPTS";
    private static final String RESPONSE_MESSAGE_USER_BLOCKED = "USER_BLOCKED";
    private static final String RESPONSE_MESSAGE_USER_NOT_ACTIVE = "USER_NOT_ACTIVE";
    private static final String RESPONSE_MESSAGE_SHOULD_RESET_PASSWORD = "SHOULD_RESET_PASSWORD";
    private static final String RESPONSE_MESSAGE_PASSWORD_EXPIRED = "PASSWORD_EXPIRED";
    private static final String RESPONSE_MESSAGE_MFA_REQUIRED = "MFA_REQUIRED";

    @Test
    @DisplayName("User logged in correctly")
    void givenProperEmailAndPasswordWhenPostAuthorizeThenUserIsLoggedInTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        assertEquals(response.statusCode(), HttpStatus.SC_OK);

        assertThat(response.body().jsonPath().getString("accessToken"), is(notNullValue()));
        assertThat(response.body().jsonPath().getString("refreshToken"), is(notNullValue()));
    }

    @Test
    @DisplayName("Wrong email while login")
    void givenNonExistingEmailAndCorrectPasswordWhenPostAuthorizeThenUserIsNotLoggedInTest() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongEmail();
        String bodyMessage = response.body().jsonPath().getString("message");

        assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
        assertEquals(bodyMessage, RESPONSE_MESSAGE_USER_NOT_FOUND);
    }

    @Test
    @DisplayName("Wrong password while login")
    void givenProperEmailAndIncorrectPasswordWhenPostAuthorizeThenUserIsNotLoggedInTest() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongPassword();
        String bodyMessage = response.body().jsonPath().getString("message");
        assertEquals(response.statusCode(), HttpStatus.SC_NOT_ACCEPTABLE);
        assertEquals(bodyMessage, RESPONSE_MESSAGE_BAD_PASSWORD);
    }

    @Test(invocationCount = 5)
    @DisplayName("Five times wrong password provided while login")
    void givenProperEmailAndIncorrectPasswordWhenFiveTimesSentThenUserIsBlockedTest() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongPassword();
        String bodyMessage = response.body().jsonPath().getString("message");

        assertEquals(response.statusCode(), HttpStatus.SC_NOT_ACCEPTABLE);
        assertEquals(bodyMessage, RESPONSE_MESSAGE_TOO_MANY_LOGIN_ATTEMPTS);
    }

//    @Test
//    void builderTest() {
//        Response response = AuthorizationEndpoints.userAuthBuilder("asasd", "DFgfd");
//        String
//    }





}

