package com.api.test;

import static io.restassured.RestAssured.given;
import static com.api.constant.Role.*;
import static org.hamcrest.Matchers.lessThan;
import org.testng.annotations.Test;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager2.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {
	@Test
	public void userDetailsAPITest() {

		Header autHheader = new Header("Authorization", getToken(FD));

		given().baseUri(getProperty("BASE_URI")).and().header(autHheader).accept(ContentType.JSON).when()
				.get("userdetails").then().statusCode(200).time(lessThan(2000L)).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponse.json"));
	}
}
