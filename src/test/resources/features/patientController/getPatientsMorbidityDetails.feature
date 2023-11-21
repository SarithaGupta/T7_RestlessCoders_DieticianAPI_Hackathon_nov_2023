@TRC-23
Feature: Get a Patient Morbidity Details

Background:
	Given User has correct email id and password
	When The user makes a login request using POST method
	Then API should respond with status code is "200" 
	And Return an access token
	And Assign "Dietician" role
	Given There is an existing patient 
	When Dietician sends HTTPS Request to update the patient by userId
	Then Dietician receives 200 OK Status
		
Scenario: Get A Patients Morbidity Test Result
		When Dietician pulls up a patient's morbidity details
		Then Dietician is able to see patient's morbidity list and details 		