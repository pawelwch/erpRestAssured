package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import pojos.userManagement.UserDeleteCommand;
import pojos.userPojo.User;
import pojos.userPojo.UserTypes;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OperatorsEndpoints {

    static Faker faker = new Faker();
    private static final String GET_OPERATOR_ENDPOINT = "/user";
    private static final String POST_OPERATOR_ENDPOINT = "/users-management";
    private static final String GET_OPERATORS_ENDPOINT = "/users-management/";
    private static final String GET_OPERATOR_BY_ID_ENDPOINT = "/users-management/{userId}";
    private static final String DELETE_OPERATOR_BY_ID_ENDPOINT = "/users-management/delete/{userId}";
    private static final String BLOCK_OPERATOR_BY_ID_ENDPOINT = "/users-management/block/{userId}";
    private static final String ACTIVATE_USER_ENDPOINT = "/user/activate";


    @DisplayName("Method to fetch logged in user details")
    public static Response get_loggedInUserData(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_OPERATOR_ENDPOINT);
    }

    //todo dokonczyc

    @DisplayName("Method to add/post a new user")
    public static Response post_user(String token, UserTypes userTypes) {
        User user = User.builder()
                .email(faker.internet().emailAddress())
                .phone("+48" + faker.phoneNumber().subscriberNumber(9))
                .contactPhone("+48" + faker.phoneNumber().subscriberNumber(9))
                .type(userTypes)
                .fullName(faker.name().fullName())
                .avatar(faker.avatar().image())
                .roleIds(List.of(1,2))
                .build();

        if (userTypes.equals(UserTypes.FOREMAN)) {
            user.setFactoryId(1);
        }

        return given().auth().preemptive().oauth2(token).body(user)
                .when().post(POST_OPERATOR_ENDPOINT);
    }

    @DisplayName("Method to add/post a new user")
    public static User post_user_return_user_object(String token, UserTypes userTypes) {
        User user = User.builder()
                .email(faker.internet().emailAddress())
                .phone("+48" + faker.phoneNumber().subscriberNumber(9))
                .contactPhone("+48" + faker.phoneNumber().subscriberNumber(9))
                .type(userTypes)
                .fullName(faker.name().fullName())
                .avatar(faker.avatar().image())
                .roleIds(List.of(1,2))
                .build();

        if (userTypes.equals(UserTypes.FOREMAN)) {
            user.setFactoryId(1);
        }

        return given().auth().preemptive().oauth2(token).body(user)
                .when().post(POST_OPERATOR_ENDPOINT)
                .then().extract().body().as(User.class);
    }

    @DisplayName("Method to fetch all operators")
    public static Response get_all_operators(String token) {
        String sizeList = "1000";
        return given().queryParam("size", sizeList).auth().preemptive().oauth2(token)
                .when().get(GET_OPERATORS_ENDPOINT);
    }


    @DisplayName("Method to fetch operator data by passing his id")
    public static Response get_userById(String token, int userId) {
        return given().auth().preemptive().oauth2(token).pathParam("userId", userId)
                .when().get(GET_OPERATOR_BY_ID_ENDPOINT);
    }

    @DisplayName("Method to delete a specific Operator by passing his id")
    public static Response delete_userById(String password, int userId, String token) {
        return given().body(new UserDeleteCommand(password)).pathParam("userId", userId).auth().preemptive().oauth2(token)
                .when().put(DELETE_OPERATOR_BY_ID_ENDPOINT);
    }

    @DisplayName("Method allowing to block other Operators")
    public static Response put_block_userById(String password, int userId, String token) {
        return given().body(password).pathParam("userId", userId).auth().preemptive().oauth2(token)
                .when().put(BLOCK_OPERATOR_BY_ID_ENDPOINT);
    }

    @DisplayName("Method allowing to block other Operators")
    public static Response put_edit_user(User userBody, int userId, String token) {
        return given().body(userBody).pathParam("userId", userId).auth().preemptive().oauth2(token)
                .when().put(BLOCK_OPERATOR_BY_ID_ENDPOINT);
    }

}
