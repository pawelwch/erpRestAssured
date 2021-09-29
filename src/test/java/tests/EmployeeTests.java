package tests;

import com.github.javafaker.Faker;
import endpoints.AuthorizationEndpoints;
import endpoints.EmployeeEndpoints;
import endpoints.FlatEndpoints;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.employeePojo.CreateEmployeeCommand;
import pojos.employeePojo.Employee;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EmployeeTests extends BaseClass{

    private String token;
    protected Faker faker;
    protected Helpers helpers;

    @BeforeEach
    void beforeTest() {
        token = AuthorizationEndpoints.postAuth_getToken();
        faker = new Faker();
        helpers = new Helpers();
    }

    @Test
    @DisplayName("Register a employee with random data and verify if created properly")
    void register_employee_with_random_data() {
//        int getFirstAvailableRoom = FlatEndpoints.returnFirstAvailableRoom(token);
//        EmployeePojo createEmployeeDraft = EmployeeEndpoints.post_employee_draft(token);
//        EmployeePojo finishEmployeeDraft = EmployeeEndpoints.put_employee_draft_create(token, createEmployeeDraft.getAvatarUrl(), createEmployeeDraft.getDocument(), createEmployeeDraft.getDraftId(), getFirstAvailableRoom);
//        int userId = finishEmployeeDraft.getBody().jsonPath().getInt("id");
//        assertThat(finishEmployeeDraft.getBody().jsonPath().getString("fullName"), is(notNullValue()));
//        assertThat(finishEmployeeDraft.statusCode(), is(200));
//
//        Response getCreatedUser = EmployeeEndpoints.get_employee(token, userId);
//        assertThat(getCreatedUser.statusCode(), is(200));
//        assertEquals(userId, getCreatedUser.getBody().jsonPath().getInt("id"));
//        assertEquals(finishEmployeeDraft.getBody().jsonPath().getString("fullName"), getCreatedUser.getBody().jsonPath().getString("fullName"));
    }

    @Test
    @DisplayName("Register an employee, then edit his basic phone number and check if edited")
    void register_employee_then_edit_his_basic_phone_number_and_check_if_correctly_updated(){
        int getFirstAvailableRoom = FlatEndpoints.return_first_available_room(token);
        CreateEmployeeCommand createEmployeeDraft = EmployeeEndpoints.post_employee_draft(token);
        Employee finishEmployeeDraft = EmployeeEndpoints.put_employee_draft_create(token, createEmployeeDraft.getAvatarUrl(), createEmployeeDraft.getDocument(), createEmployeeDraft.getDraftId(), getFirstAvailableRoom);
        Employee getCreatedEmployee = EmployeeEndpoints.get_employee(token, finishEmployeeDraft.getId()).then().extract().as(Employee.class);
        getCreatedEmployee.setLocalPhone("+48888777987");

        CreateEmployeeCommand createEmployeeCommand = Employee.fromCommand(getCreatedEmployee);

        Response editBasicPhoneNumber = EmployeeEndpoints.put_edit_employee_data(token, getCreatedEmployee.getId(), createEmployeeCommand);
    }


}

