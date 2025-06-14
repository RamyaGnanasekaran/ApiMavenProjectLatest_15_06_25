package org.api.servicenow.jsonschemavalidator;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIncidentJsonSchemaValidator {
	
	@Test
	public void createIncidentJsonSchemaValidator() {
		
		File inputFile = new File("./src/main/resources/Create_Incident.json");


		RestAssured.baseURI = "https://dev252473.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "Wp$hQ66bXoR-");
		
		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
				//.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(inputFile);

		Response response = inputRequest
				.when()
				.post();
		
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("./src/main/resources/Create_Incident_Response_Schema.json")));
		
	}

}
