package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SerenityRunner.class)

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = SerenityRest.given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then()
                .statusCode(200);

    }
    // Verify if the total record is 20

    @Test
    public void getTotalRecord() {
        response.body("$", hasSize(20));
    }

    // Verify if the name of id = 8228703 is equal to "Brijesh Gowda"
    @Test
    public void getNameUserId() {
        response.body("find { it.id == 8228703 }.name",
                equalTo("Brijesh Gowda"));
    }

    //Check the single ‘Name’ in the Array list (Atmaja Deshpande)
    @Test
    public void getSingleName() {
        response.body("name", hasItem("Brijesh Gowda"));
    }


    // Check the multiple ‘Names’ in the ArrayList (Girija Reddy, Shubha Varma, Laal Shukla )
    @Test
    public void getMultipleNames() {
        response.body("name", hasItems("Girija Reddy", "Shubha Varma", "Laal Shukla"));

    }

    // Verify the email of userid = 8228699 is equal “menaka_mehrotra@nitzsche.example”
    @Test
    public void getEmailOfUserId() {
        response.body("find { it.id == 8228699 }.email",
                equalTo("menaka_mehrotra@nitzsche.example"));

    }

    // Verify the status is “Active” of user name is “Shubha Varma”
    @Test
    public void validateStatusActive() {
        response.body("find { it.name == 'Shubha Varma' }.status",
                equalTo("active"));

    }


    //  Verify the Gender = male of user name is “DhanalakshmiPothuvaal”
    @Test
    public void validateGender() {
        response.body("find { it.name== 'Shubha Varma' }.gender",
                equalTo("male"));

    }

}
