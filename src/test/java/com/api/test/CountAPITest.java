package com.api.test;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager2.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Role.*;

public class CountAPITest {

	
	@Test
	public void VerifyCountAPITest() {
		
		
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then().log().all()
		.statusCode(200)
		.and()
		.time(lessThan(2000L))
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count",greaterThanOrEqualTo(0))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body("data.key",containsInAnyOrder("pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("\\resources\\response-schema\\countAPIresponseSchema-FD.json"));
	}
	
	@Test
	public void countAPITest_APIMissingAuthToken() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all().statusCode(401);
	}
}
