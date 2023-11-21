package com.dietician.backend.stepdefs.patient;


import com.dietician.backend.model.PatientInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ExcelReaderAndWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class CreateNewPatient {
    private final static Logger logger = Logger.getLogger(CreateNewPatient.class.getName());
    private String apiBaseUrl ="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";

    private String authToken;
    private PatientInfo patientInfo;
    
    public PatientInfo readDataFromSheet(String sheetName, Integer rowNumber) throws IOException {
        ExcelReaderAndWriter reader = new ExcelReaderAndWriter();
        List<Map<String, String>> testdata = reader.getData("src/test/resources/testData/requestBodyDetails.xlsx", sheetName);
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setFirstName(testdata.get(rowNumber).get("FirstName"));
        patientInfo.setLastName(testdata.get(rowNumber).get("LastName"));
        patientInfo.setContactNumber(testdata.get(rowNumber).get("ContactNumber"));
        patientInfo.setEmail(testdata.get(rowNumber).get("Email"));
        patientInfo.setAllergy(testdata.get(rowNumber).get("Allergy"));
        patientInfo.setFoodCategory(testdata.get(rowNumber).get("FoodCategory"));
        patientInfo.setDateOfBirth(testdata.get(rowNumber).get("DateOfBirth"));
        patientInfo.setDieticianId(testdata.get(rowNumber).get("DieticianId"));
        return patientInfo;
    }
    
    
    @Given("User needs to create a Patient using {string}")
    public void user_needs_to_create_a_patient_using(String endpoint) {

        String apiEndPointUri=apiBaseUrl.concat("/").concat(endpoint);
        logger.info("Cucumber Hostname URL is :: " + apiEndPointUri);
        getAuthInformation();
    }

    private void getAuthInformation(){
        String apiEndPointUri=apiBaseUrl.concat("/").concat("login");
        String request = "{\"password\":\"Worth83\",\"userLoginEmail\":\"Kanchan.basudkar@gmail.com\"}";
        Response response = given().body(request).contentType("application/JSON").post(apiEndPointUri);
        JsonPath jpath = new JsonPath(response.getBody().asString());
        authToken = jpath.getString("token");
    }


    @When("User has the patientInfo from {int} of {string}")
    public void userHasThePatientInfoFromOf(Integer rowNumber, String sheetName) throws IOException {
        patientInfo = readDataFromSheet( sheetName,  rowNumber);

    }

    @Then("user hit the post call the method")
    public void user_hit_the_post_call_the_method() {

        String apiEndPointUri = apiBaseUrl.concat("/").concat("patient");
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                //Serialization is happening --> converting Java object to json object

                .queryParam("patientInfo", gson.toJson(patientInfo))
                .header(new Header("Authorization", "Bearer " + authToken))
                .post(apiEndPointUri)
                .then()
                .statusCode(201);
    }

    @Then("Verify the status code is {string}")
    public void verify_the_status_code_is(String string) {

    }

    @Then("User get the authInformation")
    public void userGetTheAuthInformation() {

    }

    @Given("User needs to call an api")
    public void userNeedsToCallAnApi() {
    }
}
