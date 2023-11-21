package com.dietician.backend.stepdefs.morbidity;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import com.dietician.backend.stepdefs.constants.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;
import utilities.RunTimeDataReader;

public class RetrieveMorbidityConditionByTestName {
	private static String BASE_URL = "https://dietician-dev-41d9a344a720.herokuapp.com/dietician";

	// private static String USER_AUTH_TOKEN =
	// "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUb2hmYXR1bG5AZ21haWwuY29tIiwiaWF0IjoxNzAwNTM3NjcwLCJleHAiOjE3MDA1NjY0NzB9.4ZIj1lWvcg7zjuC1c3ZfKsHi9KTVoaMK7iHwtacAVJVQ7dSXvOT9T1k_9pI3rImPGl5TYVSlv5_Rjw1Zmnzwjg";

	RequestSpecification req;

	Response response;

	RequestSpecBuilder res;
	ConfigReaderAndWriter configReaderObj;
	Properties prop;
	RunTimeDataReader runTimeData;

	public RetrieveMorbidityConditionByTestName() {

		configReaderObj = new ConfigReaderAndWriter();
		prop = configReaderObj.initProp();
		runTimeData = new RunTimeDataReader();
	}

	@Given("User creates a GET request for the dietician API endpoint")

	public void user_creates_a_get_request_for_the_dietician_api_endpoint() {

		res = new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON);

		res.addHeader("Authorization", "Bearer " + runTimeData.getAuthToken());

		req = given().spec(res.build()).log().all();

	}

	@When("User sends a HTTPS request to fetch the morbidity details by using specific testname {string}")

	public void user_sends_a_https_request_to_fetch_the_morbidity_details_by_using_specific_testname(String str) {

		// Write code here that turns the phrase above into concrete actions

		System.out.println("morbidity testname =" + str);

		response = req.when().log().all().get(Endpoints.retrieveMorbidityConditionByTestName_ENDPOINT.concat(str));

	}

	@Then("User should be presented with a status {string}")

	public void user_should_be_presented_with_a_status(String string) {

		System.out.println("--------------------------------------------------");

		response.prettyPrint();

		System.out.println("--------------------------------------------------");

		response.then().statusCode(Integer.parseInt(string));

	}

	@Then("The response body should contain all the morbidity details of the patient")

	public void the_response_body_should_contain_all_the_morbidity_details_of_the_patient() {

		response.body().print();

	}

	@Given("User creates a GET request for the dietician API endpoint for morbidity")

	public void user_creates_a_get_request_for_the_dietician_api_endpoint_for_morbidity() {

		res = new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON);

		res.addHeader("Authorization", "Bearer " +runTimeData.getAuthToken());

		req = given().spec(res.build()).log().all();

	}

	@When("User sends a HTTPS request to fetch morbidity details by using invalid testname {string}")

	public void user_sends_a_https_request_to_fetch_morbidity_details_by_using_invalid_testname(String stri) {

		System.out.println("morbidity testname =" + stri);

		response = req.when().log().all().get(Endpoints.retrieveMorbidityConditionByTestName_ENDPOINT.concat(stri));

	}

	@Then("User should be presented with a error status {string}")

	public void user_should_be_presented_with_a_error_status(String string) {

		System.out.println("--------------------------------------------------");

		response.prettyPrint();

		System.out.println("--------------------------------------------------");

		response.then().statusCode(Integer.parseInt(string));

	}
}
