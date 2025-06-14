package org.api.servicenow.chaining;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostWithRequestBodyAsFile extends BaseAPIClass {

	@Test
	public void postWithRequestBodyAsFile() {
		
		File inputFile = new File("./src/main/resources/Create_Incident.json");

		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(inputFile);

		response = inputRequest
				.when()
				.post();

		//sys_id = response.body().xmlPath().get("response.result.sys_id");
		sys_id = response.body().jsonPath().get("result.sys_id");
		
		System.out.println("Sys_Id retrieved in the response is : " + sys_id);
		
		response.then().assertThat().statusCode(201);
		
		response.then().assertThat().statusCode(Matchers.equalTo(201));
		
		response.then().assertThat().body("response.result.sys_id", Matchers.equalTo(sys_id));
	
		response.then().assertThat().body("response.result.number", Matchers.containsString("INC"));
		
		response.then().assertThat().body("response.result.short_description", Matchers.containsString("Short Description for Create Incident"));
		

	}

}