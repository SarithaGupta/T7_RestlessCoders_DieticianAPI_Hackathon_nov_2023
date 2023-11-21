package com.dietician.backend.stepdefs.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dietician.backend.stepdefs.constants.Endpoints;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.testng.Assert;
import utilities.ConfigReaderAndWriter;
import utilities.RunTimeDataReader;

public class GetPatientsMorbidityDetails {

	RequestSpecification request;
	Response response;
	RequestSpecBuilder requestSpec;
	ConfigReaderAndWriter configReaderObj;
	Properties prop;
	RunTimeDataReader runTimeData;
	
	List<String> fileIDList;
	
	public GetPatientsMorbidityDetails() {
		configReaderObj = new ConfigReaderAndWriter();
		prop = configReaderObj.initProp();
		runTimeData = new RunTimeDataReader();
	}
	
	
	
	@When("Dietician pulls up a patient's morbidity details")
	public void dietician_pulls_up_a_patient_s_morbidity_details() {
		
		System.out.println();
		System.out.println();

	    
		String url = prop.getProperty("BASE_URL");
		requestSpec = new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON);
		requestSpec.addHeader("Authorization", "Bearer " + runTimeData.getAuthToken());
		request = RestAssured.given().spec(requestSpec.build()).log().all();
		response = request.when().log().all().get(Endpoints.getPatientsMorbidityDetails_ENDPOINT+runTimeData.getPatientID());

	}

	@Then("Dietician is able to see patient's morbidity list and details")
	public void dietician_is_able_to_see_patient_s_morbidity_list_and_details() {
		System.out.println("-----------------------");
		response.prettyPrint();
		System.out.println("-----------------------");
		response.then().log().all().assertThat().statusCode(200);
		
		String jsonResponse = response.asString();
		int numberOfFiles = JsonPath.read(jsonResponse, "$.length()");
		System.out.println("Number of Files found for PatientID "+runTimeData.getPatientID()+" is "+numberOfFiles);
		
		String fileName = null;
		
		for (byte i=0; i<numberOfFiles; i++) {
			String fileId = JsonPath.read(jsonResponse, "$["+i+"].fileId");
			fileName = JsonPath.read(jsonResponse, "$["+i+"].fileName");
			String uploadDate = JsonPath.read(jsonResponse, "$["+i+"].uploadDate");
			String morbidConditionStr = JsonPath.read(jsonResponse, "$["+i+"].morbidConditionStr");

			System.out.println("Patient's Morbidity Test Report Details:");
			System.out.println("PatientId = " + runTimeData.getPatientID());
			System.out.println("FileId = " + fileId);
			System.out.println("File Name = " + fileName);
			System.out.println("Time test result file was uploaded = " + uploadDate);
			System.out.println("Medical Condition diagnosed = " + morbidConditionStr);

			fileIDList = new ArrayList<String>();
			fileIDList.add(fileId);
			runTimeData.setPatientFileId(fileIDList);
		}
       // Assert.assertEquals(fileName, runTimeData.getPatientFileName());		
		
	}
}


