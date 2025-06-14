package org.api.servicenow.wiremock;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateWireMockStub {

	//@BeforeMethod
public void createStubForServiceNowIncident() {
	
	WireMock.stubFor(WireMock
			.post("/api/now/table/incident?sysparm_fields=number%2Cdescription%2Csys_id%2Ccategory%2Cshort_description")
			//.post("/api/now/table/incident")
			.willReturn(WireMock.aResponse()
			.withStatus(201)
			.withBody("{\r\n"
					+ "    \"result\": {\r\n"
					+ "        \"number\": \"INC0010014\",\r\n"
					+ "        \"sys_id\": \"7b22c822833122105ffcf629feaad373\",\r\n"
					+ "        \"short_description\": \"Short Description for Create Incident\",\r\n"
					+ "        \"description\": \"Description for Create Incident\",\r\n"
					+ "        \"category\": \"inquiry\"\r\n"
					+ "    }\r\n"
					+ "}")));
}

@Test
public void postWithRequestBodyAsFile() {
	
	File inputFile = new File("./src/main/resources/Create_Incident.json");
	
	RestAssured.baseURI = "http://localhost/api/now/table/incident";

	RequestSpecification inputRequest = RestAssured
			.given()
			.log()
			.all()
			.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(inputFile);
//			.body("{\r\n"
//					+ "\"description\":\"Description for Create Incident\",\r\n"
//					+ "\"short_description\":\"Short Description for Create Incident\"\r\n"
//					+ "}");

	Response response = inputRequest
			.when()
			.post();
	
	response.prettyPrint();
	System.out.println(response.statusCode());

	//sys_id = response.body().xmlPath().get("response.result.sys_id");
//	String sys_id = response.body().jsonPath().get("result.sys_id");
//	
//	System.out.println("Sys_Id retrieved in the response is : " + sys_id);
//	
//	response.then().assertThat().statusCode(201);
//	
//	response.then().assertThat().statusCode(Matchers.equalTo(201));
//	
//	response.then().assertThat().body("response.result.sys_id", Matchers.equalTo(sys_id));
//
//	response.then().assertThat().body("response.result.number", Matchers.containsString("INC"));
//	
//	response.then().assertThat().body("response.result.short_description", Matchers.containsString("Short Description for Create Incident"));
	

}
}
