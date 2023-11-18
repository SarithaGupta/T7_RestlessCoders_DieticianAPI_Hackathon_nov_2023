package com.dietician.backend.stepdefs.patient;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;

import com.dietician.backend.stepdefs.baseclass.BaseClass;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatients extends BaseClass{
	//private static String BASE_URL ="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
    private static String GET_ALL_PATIENTS_ENDPOINT ="/patient";
    private static String AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1heWFtMjAwOUBnbWFpbC5jb20iLCJpYXQiOjE3MDAzMzc1OTYsImV4cCI6MTcwMDM2NjM5Nn0.7PtDcFiwozGJppAYDWVwTWsy8XLhUsQAjgnZeSf0CBoRgyg0nbqrpB12_nD0n63fWZ-RrzPpIMxbAacQpmCBlg";
    
    RequestSpecification request;
    Response response;
    RequestSpecBuilder res;
   
  //@BeforeMethod
  public void basicAuth() {
	  System.out.println("testing -----");
	 /* res = new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON);
      res.addHeader("Authorization", "Bearer " + AUTH_TOKEN); // Add this line to set the Bearer token
      request = given().spec(res.build()); */
  }
    
	@Given("User creates GET Request for the Dietician API endpoint")
	public void user_creates_get_request_for_the_dietician_api_endpoint() {
		String url=	 configReaderObj.loadConfig().getProperty("BASE_URL")+GET_ALL_PATIENTS_ENDPOINT;    
        res = new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON);
        res.addHeader("Authorization", "Bearer " + AUTH_TOKEN); // Add this line to set the Bearer token
        request = given().spec(res.build());             
       
	}

	@When("User sends HTTPS Request to get all patients")
	public void user_sends_https_request_to_get_all_patients() {
		response = request.when().get(GET_ALL_PATIENTS_ENDPOINT);
		
	}

	@Then("User receives {int} OK Status with response body of all patients under particular dietician")
	public void user_receives_ok_status_with_response_body_of_all_patients_under_particular_dietician(Integer int1) {
		response.then().log().all().assertThat().statusCode(int1)
        .extract().response().asString();
	}


}
