package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SerenityRunner.class)
public class PostsExtractionTest {
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

    // 1. Extract the title of all records
    @Test
    public void extractAllTitles() {
        List<String> titles = response.extract().path("title");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Titles of all records: " + titles);
        System.out.println("------------------End of Test---------------------------");
    }

    // 2. Extract the total number of records
    @Test
    public void extractTotalRecords() {
        List<Integer> records = response.extract().path("$");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Total number of records: " + records.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 3. Extract the body of the 15th record (index 14)
    @Test
    public void extractBodyOf15thRecord() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Body of 15th record: " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4. Extract the user_id of all records
    @Test
    public void extractAllUserIds() {
        List<Integer> userIds = response.extract().path("user_id");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("User IDs of all records: " + userIds);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5. Extract the title of all records
    @Test
    public void extractAllRecordTitles() {
        List<String> titles = response.extract().path("title");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Titles of all records: " + titles);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6. Extract the title of all records whose user_id = 5914200
    @Test
    public void extractTitleByUserId() {
        List<String> titles = response.extract().path("findAll { it.user_id == 5914200 }.title");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Titles of records with user_id=5914200: " + titles);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7. Extract the body of all records whose id = 93957
    @Test
    public void extractBodyById() {
        List<String> bodies = response.extract().path("findAll { it.id == 93957 }.body");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("Bodies of records with id=93957: " + bodies);
        System.out.println("------------------End of Test---------------------------");
    }
}
