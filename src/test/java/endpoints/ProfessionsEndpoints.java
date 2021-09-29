package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.assetsPojo.professionRequestPojo.ProfessionCommand;
import pojos.assetsPojo.professionRequestPojo.ProfessionPojo;
import static io.restassured.RestAssured.given;

public class ProfessionsEndpoints {

    static Faker faker = new Faker();
    private static final String GET_PROFESSIONS_ENDPOINT = "/profession";
    private static final String POST_PROFESSION_ENDPOINT = "/profession";
    private static final String GET_PROFESSION_BY_ID_ENDPOINT = "/profession/{professionId}";
    private static final String PUT_PROFESSION_BY_ID_ENDPOINT = "/profession/{professionId}";
    private static final String DELETE_PROFESSION_BY_ID_ENDPOINT = "/profession/{professionId}";


    @DisplayName("Get all possible professions")
    public static Response get_all_professions(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_PROFESSIONS_ENDPOINT);
    }

    @DisplayName("Get specific size of professions")
    public static Response get_specific_size_of_professions(String token, Integer size) {
        return given().auth().preemptive().oauth2(token).queryParam("size", size)
                .when().get(GET_PROFESSIONS_ENDPOINT);
    }

    @DisplayName("Get all possible professions")
    public static ProfessionPojo get_all_professions_return_pojo(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_PROFESSIONS_ENDPOINT)
                .then().extract().as(ProfessionPojo.class);
    }

    @DisplayName("Add/Post a new profession")
    public static Response post_profession(String token) {
        ProfessionCommand professionCommand = new ProfessionCommand();
        professionCommand.setName(faker.job().position() + " " + faker.number().randomNumber());

        return given().auth().preemptive().oauth2(token).body(professionCommand)
                .when().post(POST_PROFESSION_ENDPOINT);
    }

    @DisplayName("Add/Post a new profession")
    public static ProfessionPojo post_profession_return_pojo(String token) {
        ProfessionCommand professionCommand = new ProfessionCommand();
        professionCommand.setName(faker.job().position() + " " + faker.number().randomNumber());

        return given().auth().preemptive().oauth2(token).body(professionCommand)
                .when().post(POST_PROFESSION_ENDPOINT)
                .then().extract().body().as(ProfessionPojo.class);
    }

    @DisplayName("Get specific profession")
    public static Response get_professionById(String token, int professionId) {
        return given().auth().preemptive().oauth2(token).pathParam("professionId", professionId)
                .when().get(GET_PROFESSION_BY_ID_ENDPOINT);
    }

    @DisplayName("Update specific profession name")
    public static Response put_professionById(String token, int professionId) {
        return given().auth().preemptive().oauth2(token).body(new ProfessionCommand(faker.job().position() + " " + faker.number().randomNumber()))
                .pathParam("professionId", professionId)
                .when().put(PUT_PROFESSION_BY_ID_ENDPOINT);
    }

    @DisplayName("Delete specific profession")
    public static Response delete_professionById(String token, int professionId) {
        return given().auth().preemptive().oauth2(token).pathParam("professionId", professionId)
                .when().delete(DELETE_PROFESSION_BY_ID_ENDPOINT);
    }

}
