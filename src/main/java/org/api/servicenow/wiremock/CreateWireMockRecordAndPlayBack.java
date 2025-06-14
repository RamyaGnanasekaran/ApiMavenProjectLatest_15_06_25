package org.api.servicenow.wiremock;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateWireMockRecordAndPlayBack {
	@Test
	public void createWireMockRecordAndPlayBack() {
		
		File inputFile = new File("./src/main/resources/Create_Incident.json");
		
		RestAssured.baseURI = "http://localhost/api/now/table/incident";

		RequestSpecification inputRequest = RestAssured
				.given()
				.log()
				.all()
//				.auth()
//				.basic("admin", "Wp$hQ66bXoR-")
				//.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(inputFile);
//				.body("{\r\n"
//						+ "\"description\":\"Description for Create Incident\",\r\n"
//						+ "\"short_description\":\"Short Description for Create Incident\"\r\n"
//						+ "}");

		Response response = inputRequest
				.when()
				.post();
		
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
