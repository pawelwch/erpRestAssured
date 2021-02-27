package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.FactoryEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FactoryTests extends BaseClass{

    @Test
    public void givenValidTokenWhenGetAllFactoriesThenAllFactoriesAreReturnedTest() {
        String token = AuthorizationEndpoints.postAuth_userAuthorized().then().extract().response().path("accessToken");
        Response response = FactoryEndpoints.getAllFactories_WithToken(token);
        int totalFactories = response.jsonPath().getInt("total");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(totalFactories, is(greaterThanOrEqualTo(1)));
    }

    @Test void givenNoTokenWhenGetAllFactoriesThenUserCanNotRetrieveFactoriesTest() {
        Response response = FactoryEndpoints.getAllFactories_WithoutToken();
        assertThat(response.statusCode(), is(403));
        assertThat(response.body().jsonPath().getString("error"), is("Forbidden"));
        givenValidTokenWhenGetAllFactoriesThenAllFactoriesAreReturnedTest();


    }


}
