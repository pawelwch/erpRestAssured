package tests;

import com.github.javafaker.Faker;
import endpoints.AuthorizationEndpoints;
import endpoints.EmployeeEndpoints;
import endpoints.FlatEndpoints;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.employeePojo.EmployeePojo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class EmployeeTests extends BaseClass{

    private String token;
    protected Faker faker;

    @BeforeMethod
    void beforeTest() {
        token = AuthorizationEndpoints.postAuth_getToken();
        faker = new Faker();
    }

    @Test
    @DisplayName("Register a employee with random data and verify if created properly")
    void register_employee_with_random_data() {
        Response availableFlats = FlatEndpoints.get_available_flats(token);
        int firstAvailableRoomId = availableFlats.getBody().jsonPath().getInt("content[0].rooms[0].id");
        EmployeePojo createEmployeeDraft = EmployeeEndpoints.post_employee_draft(token);
        Response finishEmployeeDraft = EmployeeEndpoints.put_employee_draft_create(token, createEmployeeDraft.getAvatarUrl(), createEmployeeDraft.getDocument(), createEmployeeDraft.getDraftId(), firstAvailableRoomId);
        int userId = finishEmployeeDraft.getBody().jsonPath().getInt("id");
        assertThat(finishEmployeeDraft.getBody().jsonPath().getString("fullName"), is(notNullValue()));
        assertThat(finishEmployeeDraft.statusCode(), is(200));

        Response getCreatedUser = EmployeeEndpoints.get_employee(token, userId);
        assertThat(getCreatedUser.statusCode(), is(200));
        assertEquals(userId, getCreatedUser.getBody().jsonPath().getInt("id"));
        assertEquals(finishEmployeeDraft.getBody().jsonPath().getString("fullName"), getCreatedUser.getBody().jsonPath().getString("fullName"));
    }

//    @Test
//    @DisplayName("Register an employee, then edit his basic phone number and check if edited")
//    void register_employee_edit_
}

