package com.dietician.backend.stepdefs.user;

import java.util.Properties;

import com.dietician.backend.stepdefs.constants.Endpoints;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;
import utilities.RunTimeDataReader;

public class UserLogout {

	RequestSpecification request;
	Response response;
	RequestSpecBuilder requestSpec;
	ConfigReaderAndWriter configReaderObj;
	Properties prop;
	RunTimeDataReader runTimeData;

	public UserLogout() {

		configReaderObj = new ConfigReaderAndWriter();
		prop = configReaderObj.initProp();
		runTimeData = new RunTimeDataReader();

	}

	@Given("User creates GET request to logout with valid end point")
	public void user_creates_get_request_to_logout_with_valid_end_point() {

		String url = prop.getProperty("BASE_URL");
		requestSpec = new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON);

		System.out.println("Line 42 showing auth token =" + runTimeData.getAuthToken());

		requestSpec.addHeader("Authorization", "Bearer " + runTimeData.getAuthToken());
		request = RestAssured.given().spec(requestSpec.build()).log().all();
	}

	@When("User sends valid HTTPS request to logout from Dietician API")
	public void user_sends_valid_https_request_to_logout_from_dietician_api() {
		response = request.when().log().all().get(Endpoints.userLogout_ENDPOINT);
	}

	@Then("User receives {int} OK Status with success message")
	public void user_receives_ok_status_with_success_message(int int1) {
		System.out.println("-----------------------");
		response.prettyPrint();
		System.out.println("-----------------------");
		response.then().log().all().assertThat().statusCode(int1).extract().response().asString();
	}

}
