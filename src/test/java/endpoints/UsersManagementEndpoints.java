package endpoints;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import jdk.jfr.Description;
import pojos.userPojo.User;
import pojos.userPojo.UserTypes;
import tests.UserTests;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersManagementEndpoints {

    static Faker faker = new Faker();
    private static final String GET_USER_ENDPOINT = "/user";
    private static final String POST_USER_ENDPOINT = "/users-management";


    @Description("Method to fetch logged user details")
    public static Response getUserData(String token) {
        return given().auth().preemptive().oauth2(token)
                .when().get(GET_USER_ENDPOINT);
    }

    //todo dokonczyc

    @Description("Method to add/post a new user")
    public static Response postUserBuilder(String email, String phone, String type, String fullName, String avatar, List<Integer> role, String factoryId, String token) {
        User user = User.builder()
                .email(email)
                .fullName(phone)
                .type(type)
                .fullName(fullName)
                .avatar(avatar)
                .userTypes(UserTypes.ADMINISTRATOR)
                .factoryId(factoryId).build();

        return given().auth().preemptive().oauth2(token).body(user)
                .when().post(POST_USER_ENDPOINT);
    }

    @Description("Method to add/post a new user with random data")
    public static Response postUserFaker(String type, String token) {
        User user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setType(type);
        user.setFullName(faker.name().fullName());
        user.setAvatar(faker.avatar().image());
        user.setRoleIds(List.of(1,2));
        user.setFactoryId(faker.address().firstName());

        return given().auth().preemptive().oauth2(token).body(user)
                .when().post(POST_USER_ENDPOINT);
    }


//    public static Response userAuthBuilder(String email, String password) {
//        UserAuth userAuth = UserAuth.builder().email(email).password(password).build();
//
//        return given().body(userAuth)
//                .when().post(POST_AUTH_ENDPOINT);
//    }


}
