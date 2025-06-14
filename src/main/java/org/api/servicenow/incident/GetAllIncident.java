package org.api.servicenow.incident;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncident {
	
	@Test
	public void getAllIncident(){
		
		RestAssured.baseURI = "https://dev264539.service-now.com/api/now/table/incident";
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.auth()
		.basic("admin", "toeR$U7v+Q8N")
		//.contentType(ContentType.JSON)
		.accept(ContentType.JSON);
		
		Response response = inputRequest
		.when()
		.get();
		
		response.prettyPrint();
		
		System.out.println("Statuc code of getAllIncident response is " +response.getStatusCode());
	}

}
