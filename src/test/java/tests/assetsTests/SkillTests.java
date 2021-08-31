package tests.assetsTests;

import endpoints.AuthorizationEndpoints;
import endpoints.ProfessionsEndpoints;
import endpoints.SkillEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.assetsPojo.assetsResponsePojo.AssetsResponsePojo;
import pojos.assetsPojo.assetsResponsePojo.Content;
import pojos.assetsPojo.professionRequestPojo.ProfessionPojo;
import pojos.assetsPojo.skillRequestPojo.SkillGroup;
import pojos.assetsPojo.skillRequestPojo.SkillPojo;
import tests.BaseClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SkillTests extends BaseClass {

    private String token;

    @BeforeMethod
    void beforeTest() {
         token = AuthorizationEndpoints.postAuth_getToken();
    }

    @Test
    @DisplayName("Being authorized get all skills")
    void given_active_token_check_if_can_get_all_skills_test() {
        AssetsResponsePojo response = ProfessionsEndpoints.get_all_professions(token).then().extract().as(AssetsResponsePojo.class);
        int totalElements = response.getTotalElements();

        assertThat(response.getContent().size(), greaterThan(0));
        assertThat(response.getContent().get(0).getId(), is(notNullValue()));
        assertThat(totalElements, greaterThan(0));
        assertThat(response, is(notNullValue()));
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Permission group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_Permission_group_test() {
        Response createdSkill = SkillEndpoints.post_skill(token, SkillGroup.PERMISSIONS);
        assertThat(createdSkill.statusCode(), is(201));

        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.jsonPath().getInt("id"));
        assertThat(getCreatedSkill.getStatusCode(), is(200));
        assertThat(getCreatedSkill.body().jsonPath().getString("id"), is(notNullValue()));
        assertThat(getCreatedSkill.body().jsonPath().getString("group"), is(SkillGroup.PERMISSIONS.toString()));
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Construction group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_Construction_group_test() {
        Response createdSkill = SkillEndpoints.post_skill(token, SkillGroup.CONSTRUCTION);
        assertThat(createdSkill.statusCode(), is(201));

        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.jsonPath().getInt("id"));
        assertThat(getCreatedSkill.getStatusCode(), is(200));
        assertThat(getCreatedSkill.body().jsonPath().getString("id"), is(notNullValue()));
        assertThat(getCreatedSkill.body().jsonPath().getString("group"), is(SkillGroup.CONSTRUCTION.toString()));
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Food group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_Food_group_test() {
        Response createdSkill = SkillEndpoints.post_skill(token, SkillGroup.FOOD);
        assertThat(createdSkill.statusCode(), is(201));

        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.jsonPath().getInt("id"));
        assertThat(getCreatedSkill.getStatusCode(), is(200));
        assertThat(getCreatedSkill.body().jsonPath().getString("id"), is(notNullValue()));
        assertThat(getCreatedSkill.body().jsonPath().getString("group"), is(SkillGroup.FOOD.toString()));
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with General group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_General_group_test() {
        Response createdSkill = SkillEndpoints.post_skill(token, SkillGroup.GENERAL);
        assertThat(createdSkill.statusCode(), is(201));

        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.jsonPath().getInt("id"));
        assertThat(getCreatedSkill.getStatusCode(), is(200));
        assertThat(getCreatedSkill.body().jsonPath().getString("id"), is(notNullValue()));
        assertThat(getCreatedSkill.body().jsonPath().getString("group"), is(SkillGroup.GENERAL.toString()));
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Technical group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_Technical_group_test() {
        Response createdSkill = SkillEndpoints.post_skill(token, SkillGroup.TECHNICAL);
        assertThat(createdSkill.statusCode(), is(201));

        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.jsonPath().getInt("id"));
        assertThat(getCreatedSkill.getStatusCode(), is(200));
        assertThat(getCreatedSkill.body().jsonPath().getString("id"), is(notNullValue()));
        assertThat(getCreatedSkill.body().jsonPath().getString("group"), is(SkillGroup.TECHNICAL.toString()));
    }

    @Test
    @DisplayName("Create a new skill, then get it by passing id and check if created correctly")
    void create_skill_then_get_it_by_passing_id_and_check_if_created_properly_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.PERMISSIONS).body().jsonPath().getInt("id");
        Response response = SkillEndpoints.get_skillById(token, skillId);
        int createdSkillId = response.body().jsonPath().getInt("id");

        assertEquals(response.statusCode(), 200);
        assertEquals(skillId, createdSkillId);
        assertThat(response.getBody().jsonPath().getString("name"), is(notNullValue()));
        assertThat(response.getBody().jsonPath().getString("authorName"), is(notNullValue()));
    }

    @Test
    @DisplayName("Create a skill, update it's name and check whether updated properly")
    void create_skill_then_update_name_and_check_if_updated_properly_test() {
        SkillPojo skillPojo = SkillEndpoints.post_skill_return_pojo(token, SkillGroup.CONSTRUCTION);
        SkillPojo updatedProfession = SkillEndpoints.put_skillById(token,skillPojo.getId(), SkillGroup.CONSTRUCTION).then().extract().body().as(SkillPojo.class);
        Response retrieveUpdatedProfession = SkillEndpoints.get_skillById(token,skillPojo.getId());

        assertEquals(retrieveUpdatedProfession.statusCode(), HttpStatus.SC_OK);
        assertThat(updatedProfession.getName(), is(retrieveUpdatedProfession.body().jsonPath().getString("name")));
    }

    @Test
    @DisplayName("Create a skill, update name, and check whether updated properly")
    void create_skill_then_update_name_and_check_if_name_updated_properly_test() {
        SkillPojo createdSkill = SkillEndpoints.post_skill(token, SkillGroup.PERMISSIONS).then().extract().as(SkillPojo.class);
        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.getId());
        assertEquals(getCreatedSkill.statusCode(), 200);

        SkillPojo updateSkill = SkillEndpoints.put_skillById(token, createdSkill.getId(), SkillGroup.PERMISSIONS).then().extract().as(SkillPojo.class);
        Response getUpdatedSkill = SkillEndpoints.get_skillById(token, createdSkill.getId());

        assertEquals(getUpdatedSkill.statusCode(), 200);
        assertThat(createdSkill.getName(), is(not(equalTo(updateSkill.getName()))));
        assertThat(createdSkill.getId(), is(equalTo(updateSkill.getId())));
    }

    @Test
    @DisplayName("Create a skill, delete it, and check whether deleted properly")
    void create_skill_then_delete_and_check_if_deleted_properly_test() {
        SkillPojo createdSkill = SkillEndpoints.post_skill(token, SkillGroup.PERMISSIONS).then().extract().as(SkillPojo.class);
        Response getCreatedSkill = SkillEndpoints.get_skillById(token, createdSkill.getId());
        assertEquals(getCreatedSkill.statusCode(), 200);

        Response deleteSkill = SkillEndpoints.delete_skillById(token, createdSkill.getId());
        assertEquals(deleteSkill.statusCode(), 204);
        Response getDeletedSkill = SkillEndpoints.get_skillById(token, createdSkill.getId());
        assertThat(getDeletedSkill.statusCode(), is(404));
        assertThat(getDeletedSkill.getBody().jsonPath().getString("message"), is("Skill not found"));
    }

}
