package org.api.servicenow.incident;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostWithRequestBodyAsFile {

	@Test
	public void PostWithRequestBodyAsFile() {
		
		File inputFile = new File("./src/main/resources/Create_Incident.json");

		RestAssured.baseURI = "https://dev252473.service-now.com/api/now/table/incident";

		RequestSpecification inputRequest = RestAssured
				.given()
				.auth()
				.basic("admin", "Wp$hQ66bXoR-")
				.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
				.contentType(ContentType.JSON)
				.accept(ContentType.XML)
				.body(inputFile);

		Response response = inputRequest.when().post();

		System.out.println("The Response status code is : " + response.statusCode());

		response.prettyPrint();

		// String sys_Id = response.body().jsonPath().get("result.sys_id");
		
		//String sys_Id = response.body().jsonPath().get("result.sys_id").toString();
		
		String sys_Id = response.body().xmlPath().get("response.result.sys_id");
		String sys_Id1 = response.body().xmlPath().getString(sys_Id);
		
		System.out.println("Sys_Id retrieved in the response is : " + sys_Id);

	}

}