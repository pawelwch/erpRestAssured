package tests;

import com.aventstack.extentreports.Status;
import endpoints.AuthorizationEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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
    @DisplayName("While login, pass correct credentials and check if user is logged in")
    void given_existing_user_data_check_if_authorized_correctly_test() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        assertEquals(response.statusCode(), HttpStatus.SC_OK);

        assertThat(response.body().jsonPath().getString("accessToken"), is(notNullValue()));
        assertThat(response.body().jsonPath().getString("refreshToken"), is(notNullValue()));

        extentReports.createTest("While login, pass the correct credentials and check if are logged in")
                .log(Status.PASS, "The user is logged in");
    }

    @Test
    @DisplayName("While login, pass wrong email and correct password and check if the user is logged in")
    void given_wrong_email_try_to_login_test() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongEmail();
        String bodyMessage = response.body().jsonPath().getString("message");

        assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
        assertThat(bodyMessage, is((RESPONSE_MESSAGE_USER_NOT_FOUND)));

        extentReports.createTest("While login, pass wrong email and correct password and check if the user is logged in")
                .log(Status.FAIL, "The user is not logged in");
    }

    @Test
    @DisplayName("While login, pass correct email and wrong password and check if the user is logged in")
    void given_correct_email_and_wrong_password_check_if_can_login_test() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongPassword();
        String bodyMessage = response.body().jsonPath().getString("message");
        assertEquals(response.statusCode(), HttpStatus.SC_NOT_ACCEPTABLE);
        assertEquals(bodyMessage, RESPONSE_MESSAGE_BAD_PASSWORD);

        extentReports.createTest("While login, pass correct email and wrong password and check if the user is logged in")
                .log(Status.FAIL, "The user is not logged in");
    }

    @RepeatedTest(5)
    @DisplayName("While login, pass wrong password `n` number of times and check if too many login attempts error")
    void given_wrong_password_specific_number_of_times_check_if_too_many_attempts_error_appears_test() {
        Response response = AuthorizationEndpoints.postAuth_userNotAuthorized_WrongPassword();
        String bodyMessage = response.body().jsonPath().getString("message");

        assertEquals(response.statusCode(), HttpStatus.SC_NOT_ACCEPTABLE);
        assertEquals(bodyMessage, RESPONSE_MESSAGE_TOO_MANY_LOGIN_ATTEMPTS);

        extentReports.createTest("While login, pass incorrect credentials specific number of times and check if user can not be logged in because too many attempts")
                .log(Status.FAIL, "The user is not logged in - too many attempts");
    }


}

