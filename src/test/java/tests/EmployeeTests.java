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

public class EmployeeTests extends BaseClass{

    private String token;
    private Faker faker;

    @BeforeMethod
    void beforeTest() {
        token = AuthorizationEndpoints.postAuth_getToken();
        faker = new Faker();

    }

    @Test
    @DisplayName("Register a random employee")
    void register_random_employee() {
        EmployeePojo createEmployeeDraft = EmployeeEndpoints.post_employee_draft(token);
        EmployeePojo putEmployeeBasicData = EmployeeEndpoints.put_employee_draft_with_basic_data(token, createEmployeeDraft.getAvatarUrl(), createEmployeeDraft.getDraftId(), createEmployeeDraft.getDocument());
        Response finishEmployeeDraft = EmployeeEndpoints.put_employee_draft_create(token , createEmployeeDraft.getAvatarUrl(), 1, 2, createEmployeeDraft.getDraftId(), createEmployeeDraft.getDocumentNumber(), createEmployeeDraft.getIdentityNumber(), createEmployeeDraft.getDateOfBirth(), createEmployeeDraft.getNationality());
    }
}

