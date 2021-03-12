package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    @BeforeClass
    public void setupConfiguration(){
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * NEW TEST * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        RestAssured.baseURI = "https://core.test.ge.4soft.dev/";
        //RestAssured.basePath = "v2";

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

}
