@tag
Feature: User Logout 

Scenario: Check if user is able to Logout
	Given User creates GET request to logout with valid end point
	When User sends valid HTTPS request to logout from Dietician API
	Then User receives 200 OK Status with success message