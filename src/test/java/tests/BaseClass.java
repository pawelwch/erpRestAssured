package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class BaseClass {

    ExtentReports extentReports;

    @BeforeEach
    public void setup_configuration(){
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * NEW TEST * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        RestAssured.baseURI = "https://core.test.ge.4soft.dev/";
        //RestAssured.basePath = "v2";
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html");

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Giewont Extent Report");
        extentReports.attachReporter(sparkReporter);


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured());
        RestAssured.requestSpecification = requestSpecBuilder.build();

    }

    @AfterEach
    public void afterTestClass(){
        extentReports.flush();
    }
}
