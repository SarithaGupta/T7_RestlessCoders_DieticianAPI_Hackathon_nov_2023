package com.dietician.backend.stepdefs.morbidity;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Properties;

import org.testng.Assert;

import com.dietician.backend.stepdefs.constants.Endpoints;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;

public class GetAllMorbidities  {
	
	RequestSpecification req;
    Response response;
    RequestSpecBuilder rs;
    ConfigReaderAndWriter configReaderObj;
  	Properties prop; 
  	


   
    public GetAllMorbidities() {
        configReaderObj = new ConfigReaderAndWriter();
        prop = configReaderObj.init_prop();
    }

    @Given("User creates the Get request to view all morbidities")
public void user_creates_the_get_request_to_view_all_morbidities_with_valid_endpoint() {
	String url=	prop.getProperty("BASE_URL");  				
    rs = new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON);
    rs.addHeader("Authorization", "Bearer " + prop.getProperty("AUTH_TOKEN"));
    req = given().spec(rs.build()); 
   
}

    @When("User sends HTTPS with valid End point requets to get all morbidities")
public void user_sends_https_requets_to_get_all_morbidities() {
	
	
	response = req.when().get(Endpoints.getAllMorbidities_ENDPOINT);
}

@Then("User receives {int} ok status with response body of all the morbidities")
public void user_receives_ok_status_with_response_body_of_all_the_morbidities(Integer int1) {

	 response.then().log().all().assertThat().statusCode(int1).extract().response().asString();
		int statuscode=response.getStatusCode();
	   System.out.println("Statuscode:" +statuscode);
	
	   //response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema	
			// (new File("./src/test/resources/schema/schemaGetAllMorbidities.json")));
	   
	
}

@When("User sends HTTPS requets with invalid endpoint to get all morbidities")
public void user_creates_the_get_request_to_view_all_morbidities_with_invalid_endpoint() {
	response = req.when().get(Endpoints.invalid_ENDPOINT);
}

@Then("User receives {int} NotFound status")
public void user_receives_bad_request_status(Integer int1) {
	response.then().log().all().assertThat().statusCode(int1);
	int statuscode=response.getStatusCode();
	System.out.println("Statuscode:"+statuscode);
	   
	response.then().log().all().assertThat().statusCode(int1);

}

}
