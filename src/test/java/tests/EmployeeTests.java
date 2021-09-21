package tests;

import com.github.javafaker.Faker;
import endpoints.AuthorizationEndpoints;
import endpoints.EmployeeEndpoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.employeePojo.EmployeePojo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class EmployeeTests extends BaseClass{

    private String token;
    private Faker faker;

    @BeforeMethod
    void beforeTest() {
        token = AuthorizationEndpoints.postAuth_getToken();
        faker = new Faker();
    }

    @Test
    @DisplayName("Register a employee with random data")
    void register_random_employee() {
        EmployeePojo createEmployeeDraft = EmployeeEndpoints.post_employee_draft(token);
        Response finishEmployeeDraft = EmployeeEndpoints.put_employee_draft_create(token, createEmployeeDraft.getAvatarUrl(), createEmployeeDraft.getDocument(), createEmployeeDraft.getDraftId());
        assertThat(finishEmployeeDraft.getBody().jsonPath().getInt("id"), is(notNullValue()));

    }

}

