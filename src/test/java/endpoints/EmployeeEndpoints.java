package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.employeePojo.CorrespondenceAddress;
import pojos.employeePojo.Document;
import pojos.employeePojo.EmployeePojo;
import pojos.employeePojo.Gender;

import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class EmployeeEndpoints {

    private static Faker faker = new Faker();
    private static File file = new java.io.File("/home/maryna/Pulpit/Projekty/Giewont_RestAssured/src/main/resources/testjpg.jpg");

    private static final String POST_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft";
    private static final String GET_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft";
    private static final String PUT_CREATE_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft/create"; // Create employee
    private static final String DELETE_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft";
    private static final String PUT_VALID_EMPLOYEE_DRAFT_ENDPOINT = "/employee-draft/valid";
    private static final String GET_EMPLOYEE = "/employee/basic/{id}";

    @DisplayName("Create employee draft")
    public static EmployeePojo post_employee_draft(String token) {
        return given().auth().preemptive().oauth2(token)
                .header("Content-type", "multipart/form-data")
                .multiPart("file", file, "image/jpeg")
                .when().post(POST_EMPLOYEE_DRAFT_ENDPOINT)
                .then().extract().as(EmployeePojo.class);
    }

//    @DisplayName("Validate draft / add employee basic data")
//    public static void put_employee_draft_with_basic_data(String token, String avatar, Integer draftId, Document document) {
//
//        EmployeePojo employeePojo = EmployeePojo.builder()
//                .avatarUrl(avatar)
//                .dateOfBirth(Instant.now().toString())
//                .documentNumber("AKI" + faker.number().digits(6))
//                .document(document)
//                .draftId(draftId)
//                .fullName(faker.name().fullName())
//                .gender(Gender.MALE.toString())
//                .nationality("POL")
//                .build();
//
//        given().auth().preemptive().oauth2(token).body(employeePojo)
//                .when().put(PUT_VALID_EMPLOYEE_DRAFT_ENDPOINT);
//    }

    public static Response put_employee_draft_create( String token, String avatar, Document document, Integer draftId) {
        EmployeePojo employeePojo = EmployeePojo.builder()
                .fullName(faker.name().fullName())
                //.correspondenceAddress()
                .avatarUrl(avatar)
                .dateOfBirth(Instant.now().toString())
                .documentNumber("AKI" + faker.number().digits(6))
                .document(document)
                .draftId(draftId)
                .gender(Gender.MALE.toString())
                .nationality("POL")
                .emailAddress(faker.name().username() + "@test.com")
                .factoryId(faker.number().numberBetween(1, 15))
                //.identityNumber()
                .roomId(faker.number().numberBetween(15, 40))
                .build();

        return given().auth().preemptive().oauth2(token).body(employeePojo)
                .when().put(PUT_CREATE_EMPLOYEE_DRAFT_ENDPOINT);
    }

    public static Response get_employee(String token, int id) {
        return given().auth().oauth2(token).pathParam("id", id)
                .when().get(GET_EMPLOYEE);
    }

}
