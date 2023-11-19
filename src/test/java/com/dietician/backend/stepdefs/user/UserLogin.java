package com.dietician.backend.stepdefs.user;

import java.util.Properties;
import org.testng.Assert;
import com.dietician.backend.stepdefs.constants.Endpoints;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReaderAndWriter;


public class UserLogin /* extends BaseClass */{
	
	RequestSpecification request;
    Response response;
    RequestSpecBuilder requestSpec;
    String reqBody;
    
    JsonPath jsonPath;
    ConfigReaderAndWriter configReaderObj;
	Properties prop; 
    
    public UserLogin() {
    	//pass object through constructor
    	configReaderObj = new ConfigReaderAndWriter();
    	prop = configReaderObj.init_prop();
    }
    
    
    
	@Given("User has correct email id and password")
	public void user_has_correct_email_id_and_password() {
		String loginEmail = prop.getProperty("email");
		String loginPassword = prop.getProperty("password");
		reqBody = "{\n"

					+ "  \"password\": \""+ loginPassword +"\",\n"

					+ "  \"userLoginEmail\": \""+loginEmail+"\"\n"

					+ "}";
	}

	@When("The user makes a login request using POST method")
	public void the_user_makes_a_login_request_using_post_method() {
		requestSpec = new RequestSpecBuilder();
		requestSpec.setBaseUri(prop.getProperty("BASE_URL"));
		requestSpec.setContentType(ContentType.JSON);
		requestSpec.setBody(reqBody);
		
		request = RestAssured.given().spec(requestSpec.build()).log().all();						
		response = request.when().log().all()
				.post(Endpoints.USERLOGIN_ENDPOINT);

	}
	
	@Then("API should respond with status code is {string}")
	public void api_should_respond_with_status_code_is(String expectedstatusCode) {
		
		response.prettyPrint();
		int actualStatusCode = response.statusCode();
		int expectedstatusCode_Int = Integer.parseInt(expectedstatusCode);
		Assert.assertEquals(expectedstatusCode_Int, actualStatusCode);
	}


	@Then("Return an access token")
	public void return_an_access_token() {
		
		jsonPath = response.jsonPath();
		String AUTH_TOKEN = jsonPath.getString("token");
		System.out.println("Bearer Token generated for the session is = " + AUTH_TOKEN);
		//to set auth at properties file
		prop.setProperty("AUTH_TOKEN", AUTH_TOKEN);
		System.out.println("AUth token in properties file is = " + prop.getProperty("AUTH_TOKEN"));
	    
	}

	@Then("Assign {string} role")
	public void assign_role(String expectedRole) {
	    
		String[] roles = (jsonPath.getString("roles")).split("_");
		System.out.println(roles[1]);
		//Assert.assertEquals(roles[1],expectedRole.toUpperCase());
		
	}
}
