package com.dietician.backend.stepdefs.patient;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import com.dietician.backend.stepdefs.constants.Endpoints;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;
import utilities.RunTimeDataReader;

public class RetrievePatientFileByFileId {
	
	private static String BASE_URL ="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
    
    private static String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTYW5kaHlhTWFub2pAZ21haWwuY29tIiwiaWF0IjoxNzAwMjcyNTAyLCJleHAiOjE3MDAzMDEzMDJ9.89LkT4E7eMbf62eNDq4Q8siA5cRVC0QW_YaiM_iAi0Wik6BBexlnTvRFNJkvCfN-b_kFwxdoVvVOYVMW86bNow";
	RequestSpecification request;
    Response response;
    RequestSpecBuilder res;
    ConfigReaderAndWriter configReaderObj;
  	Properties prop; 
	RunTimeDataReader runTimeData;
    
    public RetrievePatientFileByFileId() {
     	configReaderObj = new ConfigReaderAndWriter();
    	prop = configReaderObj.initProp();
    	runTimeData = new RunTimeDataReader();
    }
    
    
    @Given("User creates GET Request for the dietician API endpoint")
    public void user_creates_get_request_for_the_dietician_api_endpoint() {
    	 res = new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON);
         res.addHeader("Authorization", "Bearer " + runTimeData.getAuthToken()); // Add this line to set the Bearer token
         request = given().spec(res.build());   
    }

	
	@When("User sends HTTPS Request to fetch the patient File by valid fileId")
	public void user_sends_https_request_to_fetch_the_patient_file_by_valid_file_id() {
		/*
		 * String fileId = ConfigReaderAndWriter.loadConfig().getProperty("fileId");
		 * System.out.println("File Id---"+fileId); response =
		 * request.when().get("/patient/testReports/viewFile/" + fileId);
		 */
		
		String fileId="655a68a49be83b1f97ce1902";
		System.out.println("File Id---"+fileId);
		response = request.when().get("/patient/testReports/viewFile/" + fileId);
	}

	@Then("User receives {int} OK Status with corresponding patient File")
	public void user_receives_ok_status_with_corresponding_patient_file(Integer int1) {
		response.body().print();
        response.then().statusCode(int1);
	}
	
	@When("User sends HTTPS request to fetch the patient File by passing invalid fileId")
	public void user_sends_https_request_to_fetch_the_patient_file_by_passing_invalid_file_id() {
		//String invalid_FileId="65580ad57fc8ae334a2da25";
		System.out.println("File Id---"+Endpoints.invalid_FileId);
		response = request.when().get("/patient/testReports/viewFile/" + Endpoints.invalid_FileId);
	}

	@Then("User receives {int} not found status with errorMessage {string}")
	public void user_receives_not_found_status_with_error_message(Integer int1, String string) {
		response.body().print();
        response.then().statusCode(int1);
	}


}
