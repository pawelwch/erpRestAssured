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
    void get_all_factories_test() {
        String token = AuthorizationEndpoints.postAuth_userAuthorized().then().extract().response().path("accessToken");
        Response response = FactoryEndpoints.get_allFactories_WithToken(token);
        int totalFactories = response.jsonPath().getInt("total");

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(totalFactories, is(greaterThanOrEqualTo(1)));
    }

    @Test
    @DisplayName("Factories not fetched because no token provided")
    void get_all_factories_being_not_authorized_test() {
        Response response = FactoryEndpoints.get_allFactories_WithoutToken();
        assertThat(response.statusCode(), is(HttpStatus.SC_FORBIDDEN));

        assertThat(response.body().jsonPath().getString("error"), is("Forbidden"));
        get_all_factories_test();
    }


}
