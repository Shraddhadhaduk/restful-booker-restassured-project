package com.restful.booker.testsuite;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class AuthToken extends TestBase {

   static String userName = "admin";
   static String password = "password123";


   @Test
    public void verifyAuthTokenCreateSuccessfully(){
       AuthPojo authPojo = new AuthPojo();
       authPojo.setUsername(userName);
       authPojo.setPassword(password);

       Response response = given()
               .header("Content-Type","application/json")
               .when()
               .body(authPojo)
               .post("/auth");
       response.prettyPrint();
       response.then().statusCode(200);
   }
}
