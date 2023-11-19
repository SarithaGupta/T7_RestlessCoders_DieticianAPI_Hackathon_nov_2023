Feature: Get All Patients 

Background:
 		Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Status line is "OK"
    And Return an access token
    And Assign "Dietician" role

Scenario: Check if user able to retrieve all patients created by particular dietician
		Given User creates GET Request for the Dietician API endpoint
		When User sends HTTPS Request to get all patients
		Then User receives 200 OK Status with response body of all patients under particular dietician