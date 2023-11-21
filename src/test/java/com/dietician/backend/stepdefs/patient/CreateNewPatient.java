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
import utilities.AuthInfoBuilder;
import utilities.ConfigReaderAndWriter;
import utilities.ExcelReaderAndWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class CreateNewPatient {
    private final static Logger logger = Logger.getLogger(CreateNewPatient.class.getName());

    private String authToken;
    private PatientInfo patientInfo;

    private String apiEndPointUri;


    ConfigReaderAndWriter configReaderObj;
    Properties prop;

    public CreateNewPatient() {
        configReaderObj = new ConfigReaderAndWriter();
        prop = configReaderObj.initProp();
    }

    public PatientInfo readDataFromSheet(String sheetName, Integer rowNumber) throws IOException {
        ExcelReaderAndWriter reader = new ExcelReaderAndWriter();
        List<Map<String, String>> testdata = reader.getData("src/test/resources/testData/requestBodyDetails.xlsx", sheetName);
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setFirstName(testdata.get(rowNumber).get("FirstName"));
        patientInfo.setLastName(testdata.get(rowNumber).get("LastName"));
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        patientInfo.setContactNumber(Long.valueOf(number).toString());
        long numberForEmail = (long) Math.floor(Math.random() * 9_00L) + 1_00L;
        long numberForEmail1 = (long) Math.floor(Math.random() * 9_00L) + 1_00L;
        String emailValue = Long.valueOf(numberForEmail1).toString()+"_test_"+Long.valueOf(numberForEmail).toString()+"@gmail.com";
        patientInfo.setEmail(emailValue);
        patientInfo.setAllergy(testdata.get(rowNumber).get("Allergy"));
        patientInfo.setFoodCategory(testdata.get(rowNumber).get("FoodCategory"));
        patientInfo.setDateOfBirth(testdata.get(rowNumber).get("DateOfBirth"));
        patientInfo.setDieticianId(testdata.get(rowNumber).get("DieticianId"));
        return patientInfo;
    }
    @Given("User needs to create a Patient using {string}")
    public void user_needs_to_create_a_patient_using(String endPoint) {
        apiEndPointUri=prop.getProperty("BASE_URL").concat("/").concat(endPoint);
        AuthInfoBuilder authInfoBuilder = new AuthInfoBuilder();
        authToken = authInfoBuilder.getAuthInformation();
    }

    @When("User has the patientInfo from {int} of {string}")
    public void userHasThePatientInfoFromOf(Integer rowNumber, String sheetName) throws IOException {
        patientInfo = readDataFromSheet( sheetName,  rowNumber);

    }

    @Then("Verify the status code is {string}")
    public void verify_the_status_code_is(String string) {
    }

    @Then("user hit the post method")
    public void user_hit_the_post_method() {

        String apiEndPointUri = prop.getProperty("BASE_URL").concat("/").concat("patient");
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        System.out.println(gson.toJson(patientInfo));

        given()
                .config(RestAssured
                        .config()
                        .encoderConfig(EncoderConfig
                                .encoderConfig()
                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .queryParam("patientInfo", gson.toJson(patientInfo))
                .header(new Header("Authorization", "Bearer " + authToken))
                .post(apiEndPointUri)
                .then()
                .statusCode(201);
    }

    @Then("user hit the post method and attach file")
    public void user_hit_the_post_method_and_attach_file() {
        String apiEndPointUri = prop.getProperty("BASE_URL").concat("/").concat("patient");
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        System.out.println(gson.toJson(patientInfo));

        given()
                .config(RestAssured
                        .config()
                        .encoderConfig(EncoderConfig
                                .encoderConfig()
                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .multiPart(new File("src/test/resources/testData/Hypo Thyroid-Report.pdf.pdf"))
                .queryParam("patientInfo", gson.toJson(patientInfo))
                .header(new Header("Authorization", "Bearer " + authToken))
                .post(apiEndPointUri)
                .then()
                .statusCode(201);
    }



    public static void main(String a[]){
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        System.out.println(number);
        System.out.println(Long.valueOf(number).toString());
    }
}
