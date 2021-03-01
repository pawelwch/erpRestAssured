package endpoints;

import io.restassured.response.Response;
import pojos.authPojo.UserAuth;

import static io.restassured.RestAssured.given;

public class AuthorizationEndpoints {

    private static final String POST_AUTH_ENDPOINT = "/auth";

    public static Response userAuthBuilder(String email, String password) {
        UserAuth userAuth = UserAuth.builder().email(email).password(password).build();

        return given().body(userAuth)
                .when().post(POST_AUTH_ENDPOINT);
    }


    public static Response postAuth_userAuthorized() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("pawel.maryniak+1@4soft.co");
        userAuth.setPassword("Tester123!");

        return given().body(userAuth)
                .when().post(POST_AUTH_ENDPOINT);
    }

    public static Response postAuth_userNotAuthorized_WrongEmail() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("wrong@email.com");
        userAuth.setPassword("maselko");

        return given().body(userAuth)
                .when().post(POST_AUTH_ENDPOINT);
    }

    public static Response postAuth_userNotAuthorized_WrongPassword() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("pawel.maryniak+1@4soft.co");
        userAuth.setPassword("maselko");

        return given().body(userAuth)
                .when().post(POST_AUTH_ENDPOINT);
    }



}
