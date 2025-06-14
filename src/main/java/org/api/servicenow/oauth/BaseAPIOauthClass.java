package org.api.servicenow.oauth;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPIOauthClass {

	String bearer_Token;
	Response response;
	String sys_id;
	
	@BeforeTest
	public void preCondition() {

		RestAssured.baseURI = "https://dev252473.service-now.com";

	}

	@BeforeMethod
	public void generateBearerToken() {
		
		Map<String, String> formParams = new HashMap();
		formParams.put("grant_type", "password");
		formParams.put("client_id", "fd4b88f23c316210114a4f2fa33e400e");
		formParams.put("client_secret", "test123");
		formParams.put("username", "admin");
		formParams.put("password", "Wp$hQ66bXoR-");
		

	
		RequestSpecification request = RestAssured
		.given()
		.log()
		.all()
		.formParams(formParams)
		.contentType("application/x-www-form-urlencoded");
		
		Response response = request
							.when()
							.post("oauth_token");
		
		
		response.statusCode();
		response.prettyPrint();

		bearer_Token = response.body().jsonPath().getString("access_token");
		System.out.println("Bearer Token : " +bearer_Token);
}
	
	@AfterMethod
	public void postCondition() {

		response
		.then()
		.log()
		.all();
		
		response.prettyPrint();
		System.out.println("Response status code is :"+response.statusCode() );

	}
	
}
