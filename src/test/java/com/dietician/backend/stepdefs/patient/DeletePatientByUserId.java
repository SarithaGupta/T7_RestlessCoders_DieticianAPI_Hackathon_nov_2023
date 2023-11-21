package com.dietician.backend.stepdefs.patient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import utilities.AuthInfoBuilder;
import utilities.ConfigReaderAndWriter;
import utilities.ExcelReaderAndWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DeletePatientByUserId {

    ConfigReaderAndWriter configReaderObj;
    Properties prop;

    private String patientIdForDelete;
    private String authToken;
    private String apiEndPointUri;
    public DeletePatientByUserId() {
        configReaderObj = new ConfigReaderAndWriter();
        prop = configReaderObj.initProp();
    }

    public void readDataFromSheet(String sheetName, Integer rowNumber) throws IOException {
        ExcelReaderAndWriter reader = new ExcelReaderAndWriter();
        List<Map<String, String>> testdata = reader.getData("src/test/resources/testData/requestBodyDetails.xlsx", sheetName);
        patientIdForDelete =  testdata.get(rowNumber).get("patientId");
    }

    @Given("User needs to delete a Patient using {string}")
    public void user_needs_to_delete_a_patient_using(String endPoint) {
        apiEndPointUri=prop.getProperty("BASE_URL").concat("/").concat(endPoint);
        AuthInfoBuilder authInfoBuilder = new AuthInfoBuilder();
        authToken = authInfoBuilder.getAuthInformation();

    }

    @When("User has the patientId from {int} of {string}")
    public void user_has_the_patient_id_from_of(Integer rowNumber, String sheetName) throws IOException {
        readDataFromSheet( sheetName,  rowNumber);
    }

    @Then("user calls the delete api for status {string}")
    public void user_calls_the_delete_api(String statusCode) {
        String deleteApiEndPointUrl = apiEndPointUri.concat("/").concat(patientIdForDelete);
        given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header(new Header("Authorization", "Bearer " + authToken))
                .delete(deleteApiEndPointUrl)
                .then()
                .statusCode(Integer.parseInt(statusCode));
    }
}
