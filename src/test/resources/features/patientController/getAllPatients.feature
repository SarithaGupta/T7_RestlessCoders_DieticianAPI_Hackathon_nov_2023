Feature: Get All Patients 

Scenario: Check if user able to retrieve all patients created by particular dietician
Given User creates GET Request for the Dietician API endpoint
When User sends HTTPS Request to get all patients
Then User receives 200 OK Status with response body of all patients under particular dietician