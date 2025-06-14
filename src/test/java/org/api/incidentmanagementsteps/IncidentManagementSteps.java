package org.api.incidentmanagementsteps;

import java.io.File;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IncidentManagementSteps {

	RequestSpecification inputRequest;
	Response response;
	public static String sys_id;

	@Given("Set the endpoint for Incident management Api")
	public void set_End_Point() {
		RestAssured.baseURI = "https://dev309079.service-now.com/api/now/table/incident";
	}

	@Given("Set the user credentials for authenticating the system")
	public void set_Credentials() {
		inputRequest = RestAssured.given().auth().basic("admin", "nqb=xEGPN79%");
		inputRequest.log().all();
	}

	@Given("Set the queryparameter in the input request")
	public void set_Query_Params() {
		inputRequest.queryParam("sysparm_fields", "number,description,sys_id,category,short_description");
	}

	@Given("Set the Content Type for the input request")
	public void set_contenet_type() {
		inputRequest.contentType(ContentType.JSON);
	}

	@When("User send the POST request to the Incident management APi")
	public void set_post_request() {
		response = inputRequest.when().post();
		response.prettyPrint();
		sys_id = response.jsonPath().getString("result.sys_id");
		System.out.println(sys_id);
		//response.jsonPath().get("result.sys_id").toString();
	}

	@When("User send the GET request to the Incident management APi")
	public void set_get_request() {
		response = inputRequest.when().get();
		response.prettyPrint();
	}

	@Given("Set the accept for the input request")
	public void set_accept() {
	 inputRequest.accept(ContentType.JSON);
	}

	@Given("Set the file {string} in the input request")
	public void set_file(String inputFile) {
		File inputFile1 = new File("./src/main/resources/"+inputFile+".json");
		inputRequest.body(inputFile1);
	
	}
	
	@When("User send the PUT request to the Incident management APi")
	public void set_put_request() {
		response = inputRequest.when().put(sys_id);
		response.prettyPrint();
	}
	
	@Then("Validate the status code is {int}")
	public void validate_status_code(Integer statusCode) {
		response.then().assertThat().statusCode(Matchers.equalTo(statusCode));
	}

}
