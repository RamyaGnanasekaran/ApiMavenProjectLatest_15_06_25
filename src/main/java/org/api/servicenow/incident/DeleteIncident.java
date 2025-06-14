package org.api.servicenow.incident;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteIncident {

	
	@Test
	public void updateIncident() {

		RestAssured.baseURI = "https://dev252473.service-now.com/api/now/table/incident";

		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "Wp$hQ66bXoR-");

		Response response = inputRequest
				.when()
				.delete("/10387c8e83a522105ffcf629feaad33b");

		System.out.println("The Response status code is : " + response.statusCode());
		response.prettyPrint();

	}
	
}
