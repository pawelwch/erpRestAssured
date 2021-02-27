package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pojos.authPojo.UserAuth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserTests extends BaseClass{

    @Test
    void givenLoggedInUserDataWhenGetUserDetailsThenAllUserDataIsReturnedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized().then().extract().response();
        String token = response.getBody().jsonPath().getString("accessToken");
        Response getUserResponse = UserEndpoints.getUser(token);

        assertEquals(getUserResponse.statusCode(), 200);
        assertThat(getUserResponse.body().jsonPath().getString("id"), notNullValue());
        assertThat(getUserResponse.body().jsonPath().getString("email"), notNullValue());

    }
}
