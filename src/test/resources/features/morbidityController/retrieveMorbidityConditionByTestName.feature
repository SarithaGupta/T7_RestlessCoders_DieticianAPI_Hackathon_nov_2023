@TCR-18
Feature: Title of your feature
  I want to use this template for my feature file

Background:
		Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Return an access token
    And Assign "Dietician" role

 @tag1

Scenario: to retrieve morbidity condition by test name

Given User creates a GET request for the dietician API endpoint
When User sends a HTTPS request to fetch the morbidity details by using specific testname "TSH"
Then User should be presented with a status "200"
And The response body should contain all the morbidity details of the patient

Scenario: to retrieve morbidity condition by test name

Given User creates a GET request for the dietician API endpoint for morbidity
When User sends a HTTPS request to fetch morbidity details by using invalid testname "TSRA"
Then User should be presented with a error status "404"
