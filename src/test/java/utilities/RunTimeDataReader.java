package utilities;

import java.util.List;

public class RunTimeDataReader {

	
	private static String envFromTestNG;
	private static String authToken;
	private static String dieticianId;
	
	private static int patientID;
	private static String patientLoginEmail;
	private static String patientFileName;
	private static List<String> patientFileId;

	public List<String> getPatientFileId() {
		return patientFileId;
	}

	public void setPatientFileId(List<String> fileIDList) {
		RunTimeDataReader.patientFileId = fileIDList;
	}

	public String getPatientFileName() {
		return patientFileName;
	}

	public void setPatientFileName(String patientFileName) {
		RunTimeDataReader.patientFileName = patientFileName;
	}

	public String getPatientLoginEmail() {
		return patientLoginEmail;
	}

	public void setPatientLoginEmail(String patientLoginEmail) {
		RunTimeDataReader.patientLoginEmail = patientLoginEmail;
	}
	

	public  int getPatientID() {
		return patientID;
	}

	public  void setPatientID(int patientID) {
		RunTimeDataReader.patientID = patientID;
	}

	public String getDieticianId() {
		return dieticianId;
	}

	public void setDieticianId(String dieticianId) {
		RunTimeDataReader.dieticianId = dieticianId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String token) {
		authToken = token;
	}

	public String getEnvFromTestNG() {
		return envFromTestNG;
	}

	public void setEnvFromTestNG(String browser) {
		envFromTestNG = browser;
	}
}
