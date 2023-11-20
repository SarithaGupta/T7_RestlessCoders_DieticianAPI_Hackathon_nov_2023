@tag
Feature: Get Patients morbidity Details

 Background:
    Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Status line is "OK"
    And Return an access token
    And Assign "Dietician" role
 
  Scenario: Get List of Morbidity Details with correct endpoint
  
    When The user makes a GET request with correct endpoint with access token
    Then API should respond with status code is "200" 
    And Status line is "OK"
    And Should get a list of "15" mobidities

  Scenario: Get List of Morbidity Details without access token
   
    When The user makes a GET request with correct endpoint without access token
    Then User should get a response status "401 Unauthorized"
    And Response body shoudld contain an attribute "error" with value "Unauthorized"
    And "path" showing showing correct endpoint of "/dietician/morbidity"