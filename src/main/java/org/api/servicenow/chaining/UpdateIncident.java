package org.api.servicenow.chaining;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateIncident extends BaseAPIClass{
	
	@Test(dependsOnMethods = {"org.api.servicenow.chaining.PostWithRequestBodyAsFile.postWithRequestBodyAsFile"})
	//@Test
	public void updateIncident() {

		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
				.contentType(ContentType.JSON)
				.accept(ContentType.XML)
				.body("{\"description\":\"Update incident response description\",\"short_description\":\"Update incident response short description\"}");

		response = inputRequest
				.when()
				.put(sys_id);
		
		response.then().log().all();
		
		sys_id = response.body().xmlPath().get("response.result.sys_id");
		
		System.out.println("Sys_Id retrieved in the response is : " + sys_id);

	}

}
