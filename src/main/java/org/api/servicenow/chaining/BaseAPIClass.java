package org.api.servicenow.chaining;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseAPIClass {
	
	public static String sys_id;
	public Response response;
	
	@BeforeTest
	public void preCondition() {
		
		RestAssured.baseURI = "https://dev252473.service-now.com/api/now/table/incident";
		
		RestAssured.authentication = RestAssured.basic("admin", "Wp$hQ66bXoR-");
	}


	@AfterMethod
	public void postCondition() {
		
		response.then().log().all();
		
		System.out.println("The Response status code is : " + response.statusCode());

		response.prettyPrint();
	}
	
}
