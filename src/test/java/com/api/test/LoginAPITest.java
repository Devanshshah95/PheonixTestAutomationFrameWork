package com.api.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.pojo.userCredentials;
import static com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;

public class LoginAPITest {

	@Test

	public void loginApiTest() {
		// RestAssured Code.
		userCredentials usercreds = new userCredentials("iamfd", "password");
		given()
			.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(usercreds)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when().post("login").then().log().all()
		.statusCode(200).and().time(lessThan(5000L)).and()
				.body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}
