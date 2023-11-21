@logout
Feature: User Logout 

Background:
   Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Return an access token
    And Assign "Dietician" role

Scenario: Check if user is able to Logout
	Given User creates GET request to logout with valid end point
	When User sends valid HTTPS request to logout from Dietician API
	Then User receives 200 OK Status with success message