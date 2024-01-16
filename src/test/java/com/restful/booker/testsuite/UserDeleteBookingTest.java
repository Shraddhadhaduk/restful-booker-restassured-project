package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserDeleteBookingTest extends TestBase {

    @Test
    public void verifyUserDeleteSuccessfully(){
        Response response = given()
                .header("Content-Type", "application/json")
                //.contentType(ContentType.JSON)
                .header("cookie", "token=2fa463e7340d020")
                .pathParam("id", 1368)
                .when()
                .delete("/booking/{id}");
        response.prettyPrint();
        response.then().statusCode(201);

        given()
                .header("cookie", "token=2fa463e7340d020")
                .pathParam("id",1368)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(404);
    }
}
