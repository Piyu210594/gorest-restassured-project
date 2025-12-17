package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

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

    // 1. Verify that the total number of records returned is 20
    @Test
    public void getTotalRecord() {
        response.body("$", hasSize(20));
    }

    // 2. Verify that the name of the user with id = 8228688 is "Vishnu Kaur"
    @Test
    public void getNameUserId() {
        response.body("find { it.id == 8228688 }.name",
                equalTo("Vishnu Kaur"));
    }

    // 3. Verify that the response contains the name "Vishnu Kaur"
    @Test
    public void getSingleName() {
        response.body("name", hasItem("Vishnu Kaur"));
    }

    // 4. Verify that the response contains multiple names:
    //    "Vishnu Kaur", "Suma Khatri", and "Kumar Johar"
    @Test
    public void getMultipleNames() {
        response.body("name",
                hasItems("Vishnu Kaur", "Suma Khatri", "Kumar Johar"));
    }

    // 5. Verify that the email of the user with id = 8228683
    //    is "asan_mrs_ajit@goyette-kunde.test"
    @Test
    public void getEmailOfUserId() {
        response.body("find { it.id == 8228683 }.email",
                equalTo("asan_mrs_ajit@goyette-kunde.test"));
    }

    // 6. Verify that the status of the user with name "Uma Pandey MD" is "active"
    @Test
    public void validateStatusActive() {
        response.body("find { it.name == 'Uma Pandey MD' }.status",
                equalTo("active"));
    }

    // 7. Verify that the gender of the user with name "Uma Pandey MD" is "female"
    @Test
    public void validateGender() {
        response.body("find { it.name == 'Uma Pandey MD' }.gender",
                equalTo("female"));
    }
}
