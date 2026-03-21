package com.api.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.api.pojo.userCredentials;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {

	@Test

	public void loginApiTest() {
		// RestAssured Code.
		userCredentials usercreds = new userCredentials("iamfd", "password");
		given().baseUri("http://64.227.160.186:9000/v1")
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
