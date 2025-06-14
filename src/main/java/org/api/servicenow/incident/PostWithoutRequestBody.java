package org.api.servicenow.incident;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostWithoutRequestBody {

	@Test
	public void postWithoutRequestBody() {
	
		RestAssured.baseURI = "https://dev252473.service-now.com/api/now/table/incident";
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.auth()
		.basic("admin", "Wp$hQ66bXoR-")
		.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON);
		
		Response response = inputRequest
		.when()
		.post();
		
		System.out.println("The Response status code is : " + response.statusCode());
		
		response.prettyPrint();
		
	}
}
