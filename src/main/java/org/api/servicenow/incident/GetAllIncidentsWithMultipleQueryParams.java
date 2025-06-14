package org.api.servicenow.incident;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncidentsWithMultipleQueryParams {
	
	@Test
	public void getAllIncidentsWithMultipleQueryParams() {
		
		RestAssured.baseURI = "https://dev264539.service-now.com/api/now/table/incident";
		
		Map<String, String> queryParams = new HashMap();
		queryParams.put("sysparm_fields", "sys_id, category, number");
		queryParams.put("category", "software");
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.auth()
		.basic("admin", "toeR$U7v+Q8N")
		.accept(ContentType.JSON)
		.queryParams(queryParams);
		
		Response response = inputRequest
		.when()
		.get();
		
		response.prettyPrint();
		System.out.println("The response status code is " +response.getStatusCode());
		
	}

}
