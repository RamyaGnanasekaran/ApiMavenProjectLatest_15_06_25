package org.api.servicenow.incident;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostWithRequestBody {

	@Test
		public void postWithRequestBody() {
		
			RestAssured.baseURI = "https://dev252473.service-now.com/api/now/table/incident";
			
			RequestSpecification inputRequest = RestAssured
			.given()
			.auth()
			.basic("admin", "Wp$hQ66bXoR-")
			.queryParam("sysparm_fields", "number,description,sys_id,category,short_description")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body("{\"description\":\"Post response description\",\"short_description\":\"Post response short description\"}");
			
			
			Response response = inputRequest
			.when()
			.post();
			
			System.out.println("The Response status code is : " + response.statusCode());
			
			response.prettyPrint();
			
			//String sys_Id = response.body().jsonPath().get("result.sys_id");
			
			String sys_Id = response.body().jsonPath().get("result.sys_id").toString();
			System.out.println("Sys_Id retrieved in the response is : " +sys_Id);
			
		}

}
