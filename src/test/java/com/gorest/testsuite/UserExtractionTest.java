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

public class UserExtractionTest {
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

    //Extract the All Ids
    @Test
    public void extractIds() {

        List<Integer> id = response.extract().path("id"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + id);
        System.out.println("------------------End of Test---------------------------");

    }

    // Extract the all Names
    @Test
    public void extractNames() {

        List<String> names = response.extract().path("name"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + names);
        System.out.println("------------------End of Test---------------------------");

    }

    // Extract the name of 5th object
    @Test
    public void extractNameOfObject() {

        String name = response.extract().path("name[4]"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //  Extract the names of all object whose status = inactive
    @Test
    public void extractNameOfAllObject() {

        List<String> allObjectNames = response.extract().path("findAll{it.status=='inactive'}.name"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + allObjectNames);
        System.out.println("------------------End of Test---------------------------");

    }

    // Extract the gender of all the object whose status = active

    @Test
    public void extractGenderOfAllObject() {

        List<String> genderOfAll = response.extract().path("findAll{it.status=='active'}.gender"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + genderOfAll);
        System.out.println("------------------End of Test---------------------------");

    }

    // Print the names of the object whose gender = female
    @Test
    public void extractNameOfFemaleGender() {

        List<String> gender = response.extract().path("findAll{it.gender=='female'}.names"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + gender);
        System.out.println("-----------------End of Test---------------------------");

    }

    //  Get all the emails of the object where status = inactive
    @Test
    public void extractEmailsForStatusInactive() {

        List<String> emails = response.extract().path("findAll{it.status=='inactive'}.email"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + emails);
        System.out.println("-----------------End of Test---------------------------");
    }

    //  Get the ids of the object where gender = male
    @Test
    public void extractIdsForMale() {

        List<String> ids = response.extract().path("findAll{it.gender=='male'}.id"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + ids);
        System.out.println("-----------------End of Test---------------------------");
    }

    //  Get all the status
    @Test
    public void extractStatus() {

        List<String> status = response.extract().path("status"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + status);
        System.out.println("-----------------End of Test---------------------------");
    }

    // Get email of the object where name = LalDwivedi
    @Test
    public void extractName() {

        String name = response.extract().path("find{it.name=='Ghanaanand Tagore'}.email"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + name);
        System.out.println("-----------------End of Test---------------------------");
    }

    //  Get gender of id = 8228712
    @Test
    public void extractGenderForId() {

        String gender = response.extract().path("find{it.id=='8228712'}.gender"); //here we are using only json path

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + gender);
        System.out.println("-----------------End of Test---------------------------");
    }
}
