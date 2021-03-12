package endpoints;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import jdk.jfr.Description;
import pojos.authPojo.UserLogin;
import pojos.userManagement.UserDeleteCommand;
import pojos.userPojo.User;
import pojos.userPojo.UserTypes;
import tests.UserTests;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersEndpoints {

    static Faker faker = new Faker();
    private static final String GET_USER_ENDPOINT = "/user";
    private static final String POST_USER_ENDPOINT = "/users-management";
    private static final String GET_USER_BY_ID_ENDPOINT = "/users-management/{userId}";
    private static final String DELETE_USER_BY_ID_ENDPOINT = "/users-management/delete/{userId}";
    private static final String BLOCK_USER_BY_ID_ENDPOINT = "/users-management/block/{userId}";


    @Description("Method to fetch logged in user details")
    public static Response getUserData(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_USER_ENDPOINT);
    }

    //todo dokonczyc

    @Description("Method to add/post a new user")
    public static Response postUser( String token, UserTypes userTypes) {
        User user = User.builder()
                .email(faker.internet().emailAddress())
                .phone("+48" + faker.phoneNumber().subscriberNumber(9))
                .type(userTypes)
                .fullName(faker.name().fullName())
                .avatar(faker.avatar().image())
                .roleIds(List.of(1,2))
                .build();

        if (userTypes.equals(UserTypes.FOREMAN)) {
            user.setFactoryId(1);
        }

        return given().auth().preemptive().oauth2(token).body(user)
                .when().post(POST_USER_ENDPOINT);
    }


    @Description("Method to fetch user data by passing his id")
    public static Response getUserById(String token, int userId) {
        return given().auth().preemptive().oauth2(token).pathParam("userId", userId)
                .when().get(GET_USER_BY_ID_ENDPOINT);
    }

    @Description("Method to delete a specific user by passing his id")
    public static Response deleteUserById(String password, int userId, String token) {
        return given().body(new UserDeleteCommand(password)).pathParam("userId", userId).auth().preemptive().oauth2(token)
                .when().put(DELETE_USER_BY_ID_ENDPOINT);
    }

    @Description("Method allowing to block other Operators")
    public static Response blockUserById(String password, int userId, String token) {
        return given().body(password).pathParam("userId", userId).auth().preemptive().oauth2(token)
                .when().put(BLOCK_USER_BY_ID_ENDPOINT);
    }

    //todo zrobic metode do veryfikacji konta

}
