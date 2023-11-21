@TRC-21

Feature: Update an existing Patient

Background:
		Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Return an access token
    And Assign "Dietician" role

Scenario: Check if user able to update patient by userID
Given There is an existing patient 
When Dietician sends HTTPS Request to update the patient by userId
Then Dietician receives 200 OK Status