package tests;

import endpoints.AuthorizationEndpoints;
import endpoints.OperatorsEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import pojos.userPojo.EntireUserProfile;
import pojos.userPojo.User;
import pojos.userPojo.UserTypes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OperatorsTests extends BaseClass{

    @Test
    @DisplayName("After being authorized, get all user data and check if correct data returned")
    void after_login_get_user_data_and_check_if_returned_data_is_correct_test() {
        Response response = AuthorizationEndpoints.postAuth_userAuthorized();
        String token = response.getBody().jsonPath().getString("accessToken");
        Response getUserResponse = OperatorsEndpoints.get_loggedInUserData(token);

        assertEquals(getUserResponse.statusCode(), HttpStatus.SC_OK);
        assertThat(getUserResponse.body().jsonPath().getString("id"), notNullValue());
        assertThat(getUserResponse.body().jsonPath().getString("email"), notNullValue());
    }

    @Test
    @DisplayName("Create Administrator role with random data and check if created properly")
    void create_Administrator_with_random_data_and_check_if_correctly_created_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();;
        int userId = OperatorsEndpoints.post_user(token, UserTypes.ADMINISTRATOR).then().extract().body().jsonPath().getInt("id");
        Response getUserById = OperatorsEndpoints.get_userById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "ADMINISTRATOR");
        assertThat(getUserById.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("Create Central role with random data and check if created properly")
    void create_Central_with_random_data_and_check_if_correctly_created_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = OperatorsEndpoints.post_user(token, UserTypes.CENTRAL).then().extract().body().jsonPath().getInt("id");
        Response getUserById = OperatorsEndpoints.get_userById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "CENTRAL");
        assertThat(getUserById.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("Create Foreman role with random data and check if created properly")
    void create_Foreman_with_random_data_and_check_if_correctly_created_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = OperatorsEndpoints.post_user(token, UserTypes.FOREMAN).then().extract().body().jsonPath().getInt("id");
        Response getUserById = OperatorsEndpoints.get_userById(token, userId);

        assertEquals(userId, getUserById.body().jsonPath().getInt("id"));
        assertEquals(getUserById.body().jsonPath().getString("userType"), "FOREMAN");
        assertThat(getUserById.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("After creating an Administrator role, get all user data by passing his ID and check if correct data returned")
    void create_Administrator_get_him_by_passing_id_and_check_if_correctly_created_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = OperatorsEndpoints.post_user(token, UserTypes.ADMINISTRATOR).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = OperatorsEndpoints.get_userById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));
    }

    @Test
    @DisplayName("After creating an Central role, get all user data by passing his ID and check if correct data returned")
    void create_Central_get_him_by_passing_id_and_check_if_correctly_created_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = OperatorsEndpoints.post_user(token, UserTypes.CENTRAL).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = OperatorsEndpoints.get_userById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));
    }

    @Test
    @DisplayName("After creating an Foreman role, get all user data by passing his ID and check if correct data returned")
    void create_Foreman_get_him_by_passing_id_and_check_if_correctly_created_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        EntireUserProfile user = OperatorsEndpoints.post_user(token, UserTypes.FOREMAN).then().extract().body().as(EntireUserProfile.class);
        Response createdUserData = OperatorsEndpoints.get_userById(token, user.getId());

        assertThat(user.getUserProfile().getId(), is(createdUserData.body().jsonPath().getInt("id")));
        assertThat(user.getEmail(), is(createdUserData.body().jsonPath().getString("email")));
        assertThat(user.getUserProfile().getFullName(), is(equalTo(createdUserData.body().jsonPath().getString("userProfile.fullName"))));
    }

    @Test
    @DisplayName("After creating an ADMINISTRATOR role, then try to delete him and check if deleted properly")
    void create_Administrator_role_delete_him_check_if_deleted_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = OperatorsEndpoints.post_user(token, UserTypes.ADMINISTRATOR).then().extract().body().jsonPath().getInt("id");
        Response response = OperatorsEndpoints.delete_userById("maselko", userId, token);
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response deletedUser = OperatorsEndpoints.get_userById(token, userId);

        assertThat(deletedUser.statusCode(), is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    @DisplayName("After creating an CENTRAL role, then try to delete him and check if deleted properly")
    void create_Central_role_delete_him_check_if_deleted_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = OperatorsEndpoints.post_user(token, UserTypes.CENTRAL).then().extract().body().jsonPath().getInt("id");
        Response response = OperatorsEndpoints.delete_userById("maselko", userId, token);
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response deletedUser = OperatorsEndpoints.get_userById(token, userId);

        assertThat(deletedUser.statusCode(), is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    @DisplayName("After creating an FOREMAN role, then try to delete him and check if deleted properly")
    void create_Foreman_role_delete_him_check_if_deleted_test() {
        String token = AuthorizationEndpoints.postAuth_getToken();
        int userId = OperatorsEndpoints.post_user(token, UserTypes.FOREMAN).then().extract().body().jsonPath().getInt("id");
        Response response = OperatorsEndpoints.delete_userById("maselko", userId, token);
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
        Response deletedUser = OperatorsEndpoints.get_userById(token, userId);

        assertThat(deletedUser.statusCode(), is(HttpStatus.SC_NOT_FOUND));
    }



//    @Test
//    @DisplayName("After creating an Administrator user role, then try to block him and check if blocked properly")
//    void create_Administrator_role_then_verify_his_account_block_him_and_check_if_blocked_test() {
//        //1. logowanie
//        String token = AuthorizationEndpoints.postAuth_getToken();
//        //2. tworzenie Operatora
//        int userId = OperatorsEndpoints.post_user(token, UserTypes.ADMINISTRATOR).then().extract().body().jsonPath().getInt("id");
//        //todo ze wzgledu na brak mozliwosci pobrania token - user nie moze zostac zweryfikowany
//        //Response verifyResponse = UsersEndpoints.activate_user()
//        //3. blockowanie Operatora poprzez podanie jego id i hasła
//        Response response = OperatorsEndpoints.put_block_userById("Tester123!", userId, token);
//        //4. assercja czy został zablokowany
//        assertThat(response.statusCode(), is(204));
//    }

//    @Test
//    @DisplayName("Edit the Operator phone number")
//    void create_new_Administrator_edit_his_phone_number_and_check_if_updated_correctly_test(){
//        String token = AuthorizationEndpoints.postAuth_getToken();
//        User userBody = OperatorsEndpoints.post_user_return_user_object(token, UserTypes.ADMINISTRATOR);
//        String createdUserPhoneNumber = userBody.getPhone();
//
////        userBody.setPhone(Random);
////        Response editedPhone = UsersEndpoints.put_edit_user();
//
//    }




    //todo test na blokowanie usera
    //todo test na aktywacje usera
    //todo test edycji usera




}
