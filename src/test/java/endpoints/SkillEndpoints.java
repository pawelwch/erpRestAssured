package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.assetsPojo.skillRequestPojo.SkillCommand;
import pojos.assetsPojo.skillRequestPojo.SkillGroup;
import pojos.assetsPojo.skillRequestPojo.SkillPojo;

import static io.restassured.RestAssured.given;

public class SkillEndpoints {

    static Faker faker = new Faker();
    private static final String GET_SKILLS_ENDPOINT = "/skill";
    private static final String GET_SKILL_BY_ID_ENDPOINT = "/skill/{skillId}";
    private static final String POST_SKILL_ENDPOINT = "/skill";
    private static final String PUT_SKILL_BY_ID_ENDPOINT = "/skill/{skillId}";
    private static final String DELETE_SKILL_BY_ID_ENDPOINT = "/skill/{skillId}";

    @DisplayName("Get all possible skills")
    public static Response get_all_skills(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_SKILLS_ENDPOINT);
    }

    @DisplayName("Get specific size of skills(by passing a *size* parameter")
    public static Response get_specific_size_of_skills(String token, Integer size) {
        return given().auth().preemptive().oauth2(token).queryParam("size", size)
                .when().get(GET_SKILLS_ENDPOINT);
    }

    @DisplayName("Add/Post a new skill")
    public static Response post_skill(String token, SkillGroup skillGroup) {
        SkillCommand skillCommand = new SkillCommand();
        skillCommand.setName(faker.job().position() + " " + faker.number().randomNumber());
        skillCommand.setGroup(skillGroup);
        return given().auth().preemptive().oauth2(token).body(skillCommand)
                .when().post(POST_SKILL_ENDPOINT);
    }

    @DisplayName("Add/Post a new skill")
    public static SkillPojo post_skill_return_pojo(String token, SkillGroup skillGroup) {
        SkillCommand skillCommand = new SkillCommand();
        skillCommand.setName(faker.job().position() + " " + faker.number().randomNumber());
        skillCommand.setGroup(skillGroup);

        return given().auth().preemptive().oauth2(token).body(skillCommand)
                .when().post(POST_SKILL_ENDPOINT)
                .then().extract().body().as(SkillPojo.class);
    }

    @DisplayName("Get specific skill")
    public static Response get_skillById(String token, int skillId) {
        return given().auth().preemptive().oauth2(token).pathParam("skillId", skillId)
                .when().get(GET_SKILL_BY_ID_ENDPOINT);
    }

    @DisplayName("Update specific skill name")
    public static Response put_skillById(String token, int skillId) {

        return given().auth().preemptive().oauth2(token).body(new SkillCommand(faker.job().position()+ " " + faker.number().randomNumber(), SkillGroup.CONSTRUCTION))
                .pathParam("skillId", skillId)
                .when().put(PUT_SKILL_BY_ID_ENDPOINT);
    }

    @DisplayName("Delete specific skill")
    public static Response delete_skillById(String token, int skillId) {
        return given().auth().preemptive().oauth2(token).pathParam("skillId", skillId)
                .when().delete(DELETE_SKILL_BY_ID_ENDPOINT);
    }
}
