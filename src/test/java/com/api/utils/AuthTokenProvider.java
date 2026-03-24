package com.api.utils;
import static com.api.constant.Role.ENG;
import static com.api.constant.Role.FD;
import static com.api.constant.Role.QC;
import static com.api.constant.Role.SUP;
import static com.api.utils.ConfigManager2.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.api.constant.Role;
import com.api.pojo.userCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	

private AuthTokenProvider() {
		super();
		//given this is a utility class, we dont want objects to be created for this.
		//Hence we have introduced a private constructor so no object can be created outside of this class.
	}

//I want to get the token from the login request and want to extract the token and print it on console.
		
	public static String getToken(Role role) {	
		userCredentials userCredentials=null;
		if(role==FD) {
			userCredentials=new userCredentials("iamfd","password");
		}
		else if(role==QC) {
			userCredentials=new userCredentials("iamqc","password");
		}
		else if(role==ENG) {
			userCredentials=new userCredentials("iameng","password");
		}
		else if(role==SUP) {
			userCredentials=new userCredentials("iamsup","password");
		}
	
	
	String token=given()
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentials)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.extract()
		.jsonPath()
		.getString("data.token");
		return token;
	}
}
