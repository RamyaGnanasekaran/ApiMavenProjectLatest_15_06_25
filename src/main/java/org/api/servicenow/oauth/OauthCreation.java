package org.api.servicenow.oauth;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class OauthCreation extends BaseAPIOauthClass{
	
	@Test
	public void createIncidentUsingBearerToken() {
		
		File inputFile = new File("./src/main/resources/Create_Incident.json");
		
		RequestSpecification request = RestAssured
		.given()
		.log()
		.all()
		.auth()
		.oauth2(bearer_Token)
		.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(inputFile);
		
		response = request
		.when()
		.post("/api/now/table/incident");
		
		sys_id = response.body().jsonPath().getString("result.sys_id");
		System.out.println(sys_id);
		
	}

}
