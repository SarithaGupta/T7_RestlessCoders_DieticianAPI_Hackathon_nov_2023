package com.dietician.backend.stepdefs.baseclass;

import org.junit.Before;

import io.restassured.RestAssured;
import utilities.ConfigReaderAndWriter;

public class BaseClass {
	private String BASE_URL ="";
    private static final String getAllPatients_ENDPOINT ="/patient";
    private static final String userLogin_ENDPOINT ="/login";
    private static final String createNewPatient_ENDPOINT ="/patient";
    private static final String getAllMorbidities_ENDPOINT ="/morbidity";
    private static final String updatePatientByUserId_ENDPOINT ="/patient/";
    private static final String retrieveMorbidityConditionByTestName_ENDPOINT ="/morbidity/";
    private static final String getPatientsMorbidityDetails_ENDPOINT ="/patient/testReports/";
    private static final String retrievePatientFileByFileId_ENDPOINT ="patient/testReports/viewFile/";
    private static final String deletePatientByUserId_ENDPOINT ="/patient/";
    private static final String userLogout_ENDPOINT ="/logoutdietician";  
	private static final String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1heWFtMjAwOUBnbWFpbC5jb20iLCJpYXQiOjE3MDAyNjU3ODgsImV4cCI6MTcwMDI5NDU4OH0.gslNHNsxAvB_gjAS4Nxpshs4fyVDi1CTaSTGJdnb5fuHj1dXPQQEA9MEMIpFeXGz7yokDprn-wP6kko0nthIKg";
	//private static String patient_Token ="";
	//While presenting add patient token 
	private static int patient_Id ;
	private static int file_Id ;
	private static int dietician_Id ;
	private static String userEmail_Id ;
	private static String patientEmail_Id ;
	private static String userPassword ;
	private static final String patientPassword = "test";
	protected ConfigReaderAndWriter configReaderObj = new ConfigReaderAndWriter();
	
	@Before
	public void setUp() {
	
		BASE_URL = configReaderObj.loadConfig().getProperty("BASE_URL");
		
	
	}
	public void userLogin() {
	
	}
}
