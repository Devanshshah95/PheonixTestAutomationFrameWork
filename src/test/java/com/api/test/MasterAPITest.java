package com.api.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static com.api.utils.ConfigManager2.getProperty;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.constant.Role.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		given().baseUri(getProperty("BASE_URI")).header("Authorization", getToken(FD)).and()
				.contentType(ContentType.JSON).when().post("master")

				.then().log().all().statusCode(200).and().body("message", equalTo("Success"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/masterAPIresponseSchema-FD.json"))
				.body("data", hasKey("mst_oem")).body("data", hasKey("mst_model")).body("$", hasKey("message"))
				.body("data", notNullValue()).body("data.mst_model.size()", greaterThan(0))
				.body("data.mst_oem.id", everyItem(notNullValue()));
	}

	@Test
	public void invalidTokenMasterApiTest() {
		given().baseUri(getProperty("BASE_URI")).header("Authorization", "").and().contentType(ContentType.JSON).when()
				.post("master")
				.then().log().all().statusCode(401);
	}
}
