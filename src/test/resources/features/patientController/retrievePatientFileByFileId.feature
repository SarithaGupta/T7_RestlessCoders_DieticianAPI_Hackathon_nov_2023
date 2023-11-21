@TRC-24
Feature: Retrieve Patient File By FileId

Background:
 		Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Return an access token
    And Assign "Dietician" role

Scenario: Check if user able to retrieve patient File with valid File Id
Given User creates GET Request for the dietician API endpoint
When User sends HTTPS Request to fetch the patient File by valid fileId
Then User receives 200 OK Status with corresponding patient File

Scenario: Check if user able to retrieve a patient File with invalid File Id
Given User creates GET Request for the dietician API endpoint 
When User sends HTTPS request to fetch the patient File by passing invalid fileId
Then User receives 404 not found status with errorMessage "Patient Report File not found with FileId : 65580ad57fc8ae334a2da25"		