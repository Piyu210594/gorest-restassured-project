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
public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = SerenityRest.given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then()
                .statusCode(200);

    }

        // 1. Verify if the total number of records returned by the API is 25
        @Test
        public void getTotalRecord25() {
            response.body("$", hasSize(25));
        }

        // 2. Verify if the title of the user with id = 260037 matches the expected value
        @Test
        public void verifyTitleById() {
            response.body("find { it.id == 260037 }.title",
                    equalTo("Adeptio autus pauci caute hic."));
        }

        // 3. Verify if the API response contains a single specific user_id (8264000)
        @Test
        public void verifySingleUserId() {
            response.body("user_id", hasItem(8264000));
        }

        // 4. Verify if the API response contains multiple specific user_ids (8264016, 8264000, 8263995)
        @Test
        public void verifyMultipleUserIds() {
            response.body("user_id",
                    hasItems(8264016, 8264000, 8263995));
        }

        // 5. Verify the 'body' content of the user with user_id = 8264016 matches the expected string
        @Test
        public void verifyBodyByUserId() {
            response.body("find { it.user_id == 8264016 }.body",
                    equalTo("Rerum cursim cimentarius. Audacia aggredior votum. Abundans nemo carbo. Comminor volaticus cumque. A creta crur. Tremo acceptus terreo. Vinum aggredior tergo. Tamdiu sonitus omnis. Quia taedium suppono. Aegrus acer umquam. Ea apud uterque. Balbus vesica defero. Defaeco iusto traho. Thema velit capitulus. Thorax antiquus umerus. Stultus tantum strenuus. Quisquam basium vir. Hic tripudio custodia. Tondeo carpo censura."));
        }

    }
