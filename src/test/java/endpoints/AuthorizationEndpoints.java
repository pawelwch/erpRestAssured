package endpoints;

import io.restassured.response.Response;
import pojos.authPojo.UserAuth;

import static io.restassured.RestAssured.given;

public class AuthorizationEndpoints {

    private static final String authEndpoint = "/auth";

    public static Response userAuthBuilder(String email, String password) {
        UserAuth userAuth = UserAuth.builder().email(email).password(password).build();

        return given().body(userAuth)
                .when().post(authEndpoint);
    }


    public static Response postAuth_userAuthorized() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("pawel.maryniak+1@4soft.co");
        userAuth.setPassword("Tester123!");

        return given().body(userAuth)
                .when().post(authEndpoint);
    }

    public static Response postAuth_userNotAuthorized_WrongEmail() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("wrong@email.com");
        userAuth.setPassword("maselko");

        return given().body(userAuth)
                .when().post(authEndpoint);
    }

    public static Response postAuth_userNotAuthorized_WrongPassword() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("pawel.maryniak+1@4soft.co");
        userAuth.setPassword("maselko");

        return given().body(userAuth)
                .when().post(authEndpoint);
    }

//    public static Response userNotAuthorized_WrongPasswordTypedFiveTimes() {
//
//
//        for(int i = 0; i < 5; i++) {
//
//            UserAuth userAuth = new UserAuth();
//            userAuth.setEmail("wrong@email.com");
//            userAuth.setPassword("maselko");
//
//            return given()
//                    .body(userAuth)
//                    .when().post(authUrl);
//        }
//
//    }


//    public static Response incorrectEmailOrPasswordTypedFiveTimes()
//        UserAuth userAuth = new UserAuth();
//



}
