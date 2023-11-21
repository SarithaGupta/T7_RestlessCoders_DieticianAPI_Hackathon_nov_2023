package com.dietician.backend.stepdefs.patient;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.dietician.backend.model.PatientInfo;
import com.dietician.backend.stepdefs.constants.Endpoints;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;
import utilities.DynamicDataGenerator;
import utilities.RunTimeDataReader;

public class UpdatePatientByUserId {

	RequestSpecification request;
	Response response;
	RequestSpecBuilder requestSpec;
	ConfigReaderAndWriter configReaderObj;
	Properties prop;
	RunTimeDataReader runTimeData;
	JsonPath jsonPath;
	int patientID; //NOTE:PATIENT ID DECLARED AS INT
	String dieticianId;
	PatientInfo updatedPatientInfo;
	SoftAssert softAssert = new SoftAssert();
	String contact;
	String foodCategory;
	String DOB;

	
	public UpdatePatientByUserId() {
		
		configReaderObj = new ConfigReaderAndWriter();
		prop = configReaderObj.initProp();
		runTimeData = new RunTimeDataReader();
	}
	
	@Given("There is an existing patient")
	public void there_is_an_existing_patient() {
		
		System.out.println();
		System.out.println();
		System.out.println();

		
		        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

				int num = DynamicDataGenerator.randomTwoDigitGenerator();
				//Let's create an object of Patient Info:
				dieticianId = runTimeData.getDieticianId();
				String fname = "API1_"+num;
				String lname = "Tester_"+num;
				 contact = "97256988"+num;
				String email = "xyz"+num+"@gmail.com";
				String allergy = "Egg";
				 foodCategory = "NonVeg";
				 DOB = "19"+num+"-12-12";



				PatientInfo patientInfo = new PatientInfo();
				patientInfo.setFirstName(fname);
				patientInfo.setLastName(lname);
		        patientInfo.setContactNumber(contact);
		        patientInfo.setEmail(email);
		        patientInfo.setAllergy(allergy);
		        patientInfo.setFoodCategory(foodCategory);
		        patientInfo.setDateOfBirth(DOB);
		        patientInfo.setDieticianId(dieticianId);
				
		        String createPatientEndPoint = prop.getProperty("BASE_URL") + Endpoints.createNewPatient_ENDPOINT;
		        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

		        System.out.println(gson.toJson(patientInfo));

		        
		        response = given().log().all()

		        .config(RestAssured
		                .config()
		                .encoderConfig(EncoderConfig
		                        .encoderConfig()
		                        .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
		        .queryParam("patientInfo", gson.toJson(patientInfo))
		        .header(new Header("Authorization", "Bearer " + runTimeData.getAuthToken()))
		        .post(createPatientEndPoint);
				
				 
		        jsonPath = response.jsonPath();
		        patientID = jsonPath.getInt("patientId");
		        System.out.println("Patient ID of the newly created patient is = "+patientID);
		        runTimeData.setPatientID(patientID);
		        
		        
	}

	@When("Dietician sends HTTPS Request to update the patient by userId")
	public void dietician_sends_https_request_to_update_the_patient_by_user_id() {
		
		String updatePatientEndpoint = prop.getProperty("BASE_URL")+Endpoints.createNewPatient_ENDPOINT+patientID;
		System.out.println("API created for UpdatePatient is = " + updatePatientEndpoint);
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        updatedPatientInfo = new PatientInfo();
        updatedPatientInfo.setFirstName("API1_updated");//up
        updatedPatientInfo.setLastName("Tester1_updated");//up
        updatedPatientInfo.setContactNumber(contact);
        updatedPatientInfo.setEmail("xyz123_updated@gmail.com");//up
        updatedPatientInfo.setAllergy("Milk");//UP
        updatedPatientInfo.setFoodCategory(foodCategory);
        updatedPatientInfo.setDateOfBirth(DOB);
        updatedPatientInfo.setDieticianId(dieticianId);
        System.out.println(gson.toJson(updatedPatientInfo));

        String fileName = "src/test/resources/testData/Hypo Thyroid-Report.pdf.pdf";
        runTimeData.setPatientFileName(fileName);
        
        
        response = given()
                .config(RestAssured
                        .config()
                        .encoderConfig(EncoderConfig
                                .encoderConfig()
                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .multiPart(new File(fileName))
                .queryParam("patientInfo", gson.toJson(updatedPatientInfo))
                .header(new Header("Authorization", "Bearer " + runTimeData.getAuthToken()))
                .put(updatePatientEndpoint);
                
        System.out.println("-----------------------");
		response.prettyPrint();
		System.out.println("-----------------------");
	}

	@Then("Dietician receives {int} OK Status")
	public void dietician_receives_ok_status(Integer int1) {
		
		
		Integer responseStatusCode = response.getStatusCode();
		
		//validating updated response
		jsonPath = response.jsonPath();
		
		String updated_FN = updatedPatientInfo.getFirstName();
		String updated_LN = updatedPatientInfo.getLastName();
		String updated_EMAIL = updatedPatientInfo.getEmail();
		String updated_ALLERGY = updatedPatientInfo.getAllergy();
		String updated_CONTACT = updatedPatientInfo.getContactNumber();
		String updated_FOODCATEGORY = updatedPatientInfo.getFoodCategory();
		String updated_DIETICIAN_ID = updatedPatientInfo.getDieticianId();


		String response_FN = jsonPath.getString("FirstName");
		String response_LN = jsonPath.getString("LastName");
		String response_EMAIL = jsonPath.getString("Email");
		runTimeData.setPatientLoginEmail(response_EMAIL);
		String response_ALLERGY = jsonPath.getString("Allergy");
		String response_CONTACT  = jsonPath.getString("ContactNumber");
		String response_FOODCATEGORY = jsonPath.getString("FoodCategory");
		String response_DIETICIAN_ID = jsonPath.getString("DieticianId"); 

		
		softAssert.assertEquals(updated_FN, response_FN);
		softAssert.assertEquals(updated_LN, response_LN);
		softAssert.assertEquals(updated_EMAIL, response_EMAIL);
		softAssert.assertEquals(updated_ALLERGY, response_ALLERGY);
		softAssert.assertEquals(updated_CONTACT, response_CONTACT);
		softAssert.assertEquals(updated_FOODCATEGORY, response_FOODCATEGORY);
		softAssert.assertEquals(updated_DIETICIAN_ID, response_DIETICIAN_ID);
		softAssert.assertEquals(int1, responseStatusCode);

		softAssert.assertAll();
		
	
	}
}
