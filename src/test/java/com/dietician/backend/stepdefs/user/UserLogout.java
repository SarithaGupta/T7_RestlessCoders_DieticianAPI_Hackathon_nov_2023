package com.dietician.backend.stepdefs.user;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import org.testng.Assert;

import com.dietician.backend.stepdefs.constants.Endpoints;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;

public class UserLogout {

	RequestSpecification request;
    Response response;
    RequestSpecBuilder requestSpec;
    ConfigReaderAndWriter configReaderObj;
    Properties prop;
    
     public UserLogout() {
			
    	configReaderObj = new ConfigReaderAndWriter();
    	prop = configReaderObj.init_prop();
    }
    
	@Given("User creates GET request to logout with valid end point")
	public void user_creates_get_request_to_logout_with_valid_end_point() {
		System.out.println(prop.getProperty("AUTH_TOKEN"));
		String url=	prop.getProperty("BASE_URL");    
		requestSpec = new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON);
		requestSpec.addHeader("Authorization", "Bearer " + prop.getProperty("AUTH_TOKEN"));
        request = given().spec(requestSpec.build());  
	}

	@When("User sends valid HTTPS request to logout from Dietician API")
	public void user_sends_valid_https_request_to_logout_from_dietician_api() {
		response = request.when().get(Endpoints.userLogout_ENDPOINT);
	}

	@Then("User receives {int} OK Status with success message")
	public void user_receives_ok_status_with_success_message(Integer int1) {
		response.then().log().all().assertThat().statusCode(int1)
        .extract().response().asString();
		
		
	}
}
