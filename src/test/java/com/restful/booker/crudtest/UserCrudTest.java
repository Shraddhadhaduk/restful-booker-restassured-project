package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserCrudTest extends TestBase {

    static String userName = "admin";
    static String password = "password123";


    @Test
    public void test001() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(userName);
        authPojo.setPassword(password);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPojo)
                .post("/auth");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/booking");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test003() {
        Response response = given()
                .pathParam("id", 423)
                .when()
                .get("/booking/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test004() {
        HashMap<String, String> checkInOutDatesData = new HashMap<String, String>();
        checkInOutDatesData.put("checkin", "2018-01-01");
        checkInOutDatesData.put("checkout", "2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Jim");
        bookingPojo.setLastname("Brown");
        bookingPojo.setTotalprice(111);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(checkInOutDatesData);
        bookingPojo.setAdditionalneeds("Breakfast");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPojo)
                .post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test005() {

        HashMap<String, String> checkInOutDatesData = new HashMap<String, String>();
        checkInOutDatesData.put("checkin", "2018-01-01");
        checkInOutDatesData.put("checkout", "2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("James");
        bookingPojo.setLastname("Brown");
        bookingPojo.setTotalprice(111);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(checkInOutDatesData);
        bookingPojo.setAdditionalneeds("Breakfast");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("cookie", "token=5fe9394ee0a6cd8")
                .pathParam("id", 393)
                .when()
                .body(bookingPojo)
                .put("/booking/{id}");
        response.prettyPrint();
        response.then().statusCode(200);

    }
        @Test
        public void test006() {
            HashMap<String, String> checkInOutDatesData = new HashMap<String, String>();
            checkInOutDatesData.put("checkin", "2018-01-01");
            checkInOutDatesData.put("checkout", "2019-01-01");

            BookingPojo bookingPojo = new BookingPojo();
            bookingPojo.setFirstname("James");
            bookingPojo.setLastname("Brown");
            bookingPojo.setTotalprice(111);
            bookingPojo.setDepositpaid(true);
            bookingPojo.setBookingdates(checkInOutDatesData);
            bookingPojo.setAdditionalneeds("Breakfast");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("cookie", "token=c575e603bc067ef")
                    .pathParam("id", 393)
                    .when()
                    .body(bookingPojo)
                    .patch("/booking/{id}");
            response.prettyPrint();
            response.then().statusCode(200);
        }

        @Test
        public void test007 () {
            Response response = given()
                    .header("Content-Type", "application/json")
                    //.contentType(ContentType.JSON)
                    .header("cookie", "token=c575e603bc067ef")
                    .pathParam("id", 159)
                    .when()
                    .delete("/booking/{id}");
            response.prettyPrint();
            response.then().statusCode(201);

            given()
                    .pathParam("id", 159)
                    .when()
                    .get("/booking/{id}")
                    .then()
                    .statusCode(404);
        }
    }

