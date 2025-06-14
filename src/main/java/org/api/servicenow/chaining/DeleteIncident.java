package org.api.servicenow.chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteIncident extends BaseAPIClass{

	
	@Test(dependsOnMethods = {"org.api.servicenow.chaining.UpdateIncident.updateIncident"})

	public void deleteIncident() {

		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all();

		response = inputRequest
				.when()
				.delete(sys_id);

	
	}
	
}
