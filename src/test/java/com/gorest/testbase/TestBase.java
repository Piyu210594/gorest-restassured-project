package com.gorest.testbase;

import com.gorest.constants.Path;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI="https://gorest.co.in/public/v2/";

        RestAssured.basePath= Path.USER;
    }

}