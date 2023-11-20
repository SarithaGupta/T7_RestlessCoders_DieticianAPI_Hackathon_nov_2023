package com.dietician.backend.stepdefs.constants;

public class Endpoints {
	public static final String GET_ALL_PATIENTS_ENDPOINT ="/patient";
	public static final String USERLOGIN_ENDPOINT ="/login";
	public static final String createNewPatient_ENDPOINT ="/patient";
	public static final String getAllMorbidities_ENDPOINT ="/morbidity";
	public static final String updatePatientByUserId_ENDPOINT ="/patient/";
	public static final String retrieveMorbidityConditionByTestName_ENDPOINT ="/morbidity/";
	public static final String getPatientsMorbidityDetails_ENDPOINT ="/patient/testReports/";
	public static final String retrievePatientFileByFileId_ENDPOINT ="patient/testReports/viewFile/";
	public static final String deletePatientByUserId_ENDPOINT ="/patient/";
	public static final String userLogout_ENDPOINT ="/logoutdietician"; 
	private static final String patientPassword = "test";
}
