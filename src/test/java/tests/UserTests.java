package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.UsersManagementEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import pojos.userPojo.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pojos.userPojo.UserTypes.ADMINISTRATOR;

public class UserTests extends BaseClass{

    @Test
    @DisplayName("After creating a user, get all his data")
    void givenLoggedInUserDataWhenGetUserDetailsThenAllUserDataIsReturnedTest() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");
        Response getUserResponse = UsersManagementEndpoints.getUserData(token);

        assertEquals(getUserResponse.statusCode(), HttpStatus.SC_OK);
        assertThat(getUserResponse.body().jsonPath().getString("id"), notNullValue());
        assertThat(getUserResponse.body().jsonPath().getString("email"), notNullValue());
    }

    @Test
    void postUserBuilder() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");
        User user = new User(List.of(1,2), "+74 364 523 523", null, null, null, null, null, null);
        Response postUserResp = UsersManagementEndpoints
                .postUserBuilder("test@test5.pl", "+54 474 345 234", "ADMINISTRATOR", "Nowy Ziom", "", List.of(1), "3" , token);

        System.out.println(postUserResp.prettyPeek().prettyPrint());
    }
}
