package endpoints;

import io.restassured.response.Response;
import pojos.authPojo.UserLogin;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class AuthorizationEndpoints {

    private static final String POST_AUTH_ENDPOINT = "/auth";

    public static Response userAuthBuilder(String email, String password) {
        UserLogin userLogin = UserLogin.builder().email(email).password(password).build();

        return given().body(userLogin)
                .when().post(POST_AUTH_ENDPOINT);
    }

    public static Response postAuth_userAuthorized() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("admin@admin.pl");
        userLogin.setPassword("maselko");

        return given().body(userLogin)
                .when().post(POST_AUTH_ENDPOINT);
    }

    public static Response postAuth_userNotAuthorized_WrongEmail() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("wrong@email.com");
        userLogin.setPassword("maselko");

        return given().body(userLogin)
                .when().post(POST_AUTH_ENDPOINT);
    }

    public static Response postAuth_userNotAuthorized_WrongPassword() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("admin@admin.pl");
        userLogin.setPassword("wrongPassword");

        return given().body(userLogin)
                .when().post(POST_AUTH_ENDPOINT);
    }

    public static String postAuth_getToken() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("admin@admin.pl");
        userLogin.setPassword("maselko");

        return given().body(userLogin)
                .when().post(POST_AUTH_ENDPOINT).body().jsonPath().getString("accessToken");
    }

}
