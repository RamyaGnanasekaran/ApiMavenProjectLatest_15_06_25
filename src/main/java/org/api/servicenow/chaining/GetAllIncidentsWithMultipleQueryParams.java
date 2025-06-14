package org.api.servicenow.chaining;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncidentsWithMultipleQueryParams extends BaseAPIClass{
	
	@Test(dependsOnMethods = {"org.api.servicenow.chaining.PostWithRequestBodyAsFile.postWithRequestBodyAsFile"})

	public void getAllIncidentsWithMultipleQueryParams() {
		
		Map<String, String> queryParams = new HashMap();
		queryParams.put("sysparm_fields", "sys_id, category, number");
		queryParams.put("category", "software");
		
		RequestSpecification inputRequest = RestAssured
		.given()
		.log()
		.all()
		.accept(ContentType.JSON)
		.queryParams(queryParams);
		
		response = inputRequest
		.when()
		.get();
		
		response.then().assertThat().body("result.number", Matchers.hasItem("INC0009005"));
		
	
		
	}

}
