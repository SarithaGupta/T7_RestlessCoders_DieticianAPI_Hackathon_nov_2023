<<<<<<< HEAD
@TCR-17
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
=======
@tag
Feature: Verification of GetAllMorbidities

  @tag1
  Scenario: To verify thst user is able to view all morbidities with valid End point.
    Given User creates the Get request to view all morbidities 
    When User sends HTTPS with valid End point requets to get all morbidities
    Then User receives 200 ok status with response body of all the morbidities
    
  @tag2
  Scenario Outline: To verify thst user is getting 404 bad request with invalid End point.
   Given User creates the Get request to view all morbidities 
    When User sends HTTPS requets with invalid endpoint to get all morbidities
    Then User receives 404 NotFound status
    



 
>>>>>>> db1f9c5ee74c7e4dc7d52d6ce05b073f1fe447d6
