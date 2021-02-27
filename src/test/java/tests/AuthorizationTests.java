package tests;

import endpoints.AuthorizationEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.*;

public class AuthorizationTests extends BaseClass{

    private static final String responseMessage_badPassword = "BAD_PASSWORD";
    private static final String responseMessage_userNotFound = "User not found";
    private static final String responseMessage_mustBeNotNull = "must not be null";
    private static final String responseMessage_tooManyLoginAttempts = "TOO_MANY_LOGIN_ATTEMPTS";
    private static final String responseMessage_userBlocked = "USER_BLOCKED";
    private static final String responseMessage_userNotActive = "USER_NOT_ACTIVE";
    private static final String responseMessage_shouldResetPassword = "SHOULD_RESET_PASSWORD";
    private static final String responseMessage_passwordExpired = "PASSWORD_EXPIRED";
    private static final String responseMessage_mfaRequired = "MFA_REQUIRED";

    @Test
    void givenProperEmailAndPasswordWhenPostAuthorizeThenUserIsLoggedInTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        assertEquals(response.statusCode(), 200);

        assertThat(response.body().jsonPath().getString("accessToken"), is(notNullValue()));
        assertThat(response.body().jsonPath().getString("refreshToken"), is(notNullValue()));
    }


    @Test
    void givenNonExistingEmailAndCorrectPasswordWhenPostAuthorizeThenUserIsNotLoggedInTest() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongEmail();
        String bodyMessage = response.body().jsonPath().getString("message");

        assertEquals(response.statusCode(), 404);
        assertEquals(bodyMessage, responseMessage_userNotFound);
    }

    @Test
    void givenProperEmailAndIncorrectPasswordWhenPostAuthorizeThenUserIsNotLoggedInTest() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongPassword();
        String bodyMessage = response.body().jsonPath().getString("message");
        assertEquals(response.statusCode(), 406);
        assertEquals(bodyMessage, responseMessage_badPassword);
    }

    @Test(invocationCount = 5)
    void givenProperEmailAndIncorrectPasswordWhenFiveTimesSentThenUserIsBlockedTest() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongPassword();
        String bodyMessage = response.body().jsonPath().getString("message");

        assertEquals(response.statusCode(), 406);
        assertEquals(bodyMessage, responseMessage_tooManyLoginAttempts);
    }

//    @Test
//    void builderTest() {
//        Response response = AuthorizationEndpoints.userAuthBuilder("asasd", "DFgfd");
//        String
//    }





}

