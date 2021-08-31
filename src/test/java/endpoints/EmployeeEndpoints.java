package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.employeePojo.Document;
import pojos.employeePojo.EmployeePojo;
import pojos.employeePojo.Gender;

import java.io.File;
import java.time.Instant;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class EmployeeEndpoints {

    private static Faker faker = new Faker();
    private static File file = new java.io.File("/home/maryna/Pulpit/Projekty/Giewont_RestAssured/src/main/resources/testjpg.jpg");

    private static final String POST_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft";
    private static final String GET_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft";
    private static final String PUT_CREATE_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft/create"; // Create employee
    private static final String DELETE_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft";
    private static final String PUT_VALID_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft/valid";

    @DisplayName("Create employee draft")
    public static EmployeePojo post_employee_draft(String token) {
        return given().auth().preemptive().oauth2(token)
                .header("Content-type", "multipart/form-data")
                .multiPart("file", file, "image/jpeg")
                .when().post(POST_EMPLOYEE_DRAFT_ENDPOINT)
                .then().extract().as(EmployeePojo.class);
    }

    @DisplayName("Validate draft / add employee basic data")
    public static EmployeePojo put_employee_draft_with_basic_data(String token, String avatar, Integer draftId, Document document) {

        EmployeePojo employeePojo = EmployeePojo.builder()
                .avatarUrl(avatar)
                .dateOfBirth(Instant.now().toString())
                .documentNumber("AKI" + faker.number().digits(6))
                .document(document)
                .draftId(draftId)
                .fullName(faker.name().fullName())
                .gender(Gender.MALE.toString())
                .nationality("POL")
                .build();

        return given().auth().preemptive().oauth2(token).body(employeePojo)
                .when().put(PUT_VALID_EMPLOYEE_DRAFT_ENDPOINT)
                .then().extract().as(EmployeePojo.class);
    }

    public static Response put_employee_draft_create(String token, String avatar, int factoryId, int roomId, Integer draftId, String documentNumber, String identityNumber, String dateOfBirth, String nationality) {
        EmployeePojo employeePojo = EmployeePojo.builder()
                .avatarUrl(avatar)
                .documentNumber(documentNumber)
                .identityNumber(identityNumber)
                .dateOfBirth(dateOfBirth)
                .nationality(nationality)
                .factoryId(factoryId)
                .roomId(roomId)
                .draftId(draftId)

                .build();

        return given().auth().preemptive().oauth2(token).body(employeePojo)
                .when().put(PUT_CREATE_EMPLOYEE_DRAFT_ENDPOINT)
                .then().extract().response();
    }

}
