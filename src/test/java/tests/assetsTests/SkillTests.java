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
import pojos.assetsPojo.skillRequestPojo.SkillGroup;
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
    void given_active_token_check_if_can_add_skill_with_permission_group_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.PERMISSIONS).then().extract().body().jsonPath().getInt("id");
        AssetsResponsePojo response = SkillEndpoints.get_specific_size_of_skills(token, 300).then().extract().as(AssetsResponsePojo.class);
        boolean checkId = response.getContent().stream().map(Content::getId).anyMatch(id -> id.equals(skillId));

        assertTrue(checkId);
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Construction group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_construction_group_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.CONSTRUCTION).then().extract().body().jsonPath().getInt("id");
        AssetsResponsePojo response = SkillEndpoints.get_specific_size_of_skills(token, 300).then().extract().as(AssetsResponsePojo.class);
        boolean checkId = response.getContent().stream().map(Content::getId).anyMatch(id -> id.equals(skillId));

        assertTrue(checkId);
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Food group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_food_group_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.FOOD).then().extract().body().jsonPath().getInt("id");
        AssetsResponsePojo response = SkillEndpoints.get_specific_size_of_skills(token, 300).then().extract().as(AssetsResponsePojo.class);
        boolean checkId = response.getContent().stream().map(Content::getId).anyMatch(id -> id.equals(skillId));

        assertTrue(checkId);
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with General group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_general_group_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.GENERAL).then().extract().body().jsonPath().getInt("id");
        AssetsResponsePojo response = SkillEndpoints.get_specific_size_of_skills(token, 300).then().extract().as(AssetsResponsePojo.class);
        boolean checkId = response.getContent().stream().map(Content::getId).anyMatch(id -> id.equals(skillId));

        assertTrue(checkId);
    }

    @Test
    @DisplayName("Being authorized post/add a new skill with Technical group and check if added to the list")
    void given_active_token_check_if_can_add_skill_with_technical_group_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.TECHNICAL).then().extract().body().jsonPath().getInt("id");
        AssetsResponsePojo response = SkillEndpoints.get_specific_size_of_skills(token, 300).then().extract().as(AssetsResponsePojo.class);
        boolean checkId = response.getContent().stream().map(Content::getId).anyMatch(id -> id.equals(skillId));

        assertTrue(checkId);
    }

    @Test
    @DisplayName("Create a new skill, then get it by passing id and check if created correctly")
    void create_skill_then_get_it_by_passing_id_and_check_if_created_properly_test() {
        int skillId = SkillEndpoints.post_skill(token, SkillGroup.PERMISSIONS).body().jsonPath().getInt("id");
        Response response = SkillEndpoints.get_skillById(token, skillId);
        int createdSkillId = response.body().jsonPath().getInt("id");

        assertEquals(response.statusCode(), HttpStatus.SC_OK);
        assertEquals(skillId, createdSkillId);
        assertThat(response.getBody().jsonPath().getString("name"), is(notNullValue()));
        assertThat(response.getBody().jsonPath().getString("authorName"), is(notNullValue()));
    }


}
