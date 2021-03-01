package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.FactoryEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FactoryTests extends BaseClass{

    @Test
    @DisplayName("Get all factories test")
    void givenValidTokenWhenGetAllFactoriesThenAllFactoriesAreReturnedTest() {
        String token = AuthorizationEndpoints.postAuth_userAuthorized().then().extract().response().path("accessToken");
        Response response = FactoryEndpoints.getAllFactories_WithToken(token);
        int totalFactories = response.jsonPath().getInt("total");

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(totalFactories, is(greaterThanOrEqualTo(1)));
    }

    @Test
    @DisplayName("Factories not fetched because of no token provided")
    void givenNoTokenWhenGetAllFactoriesThenUserCanNotRetrieveFactoriesTest() {
        Response response = FactoryEndpoints.getAllFactories_WithoutToken();
        assertThat(response.statusCode(), is(HttpStatus.SC_FORBIDDEN));

        assertThat(response.body().jsonPath().getString("error"), is("Forbidden"));
        givenValidTokenWhenGetAllFactoriesThenAllFactoriesAreReturnedTest();
    }


}
