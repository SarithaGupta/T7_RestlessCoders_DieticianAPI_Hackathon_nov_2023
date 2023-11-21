@TRC-17
Feature: Verification of GetAllMorbidities

Background:
Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Return an access token
    And Assign "Dietician" role

  @tag1
  Scenario: To verify that user is able to view all morbidities with valid End point.
    Given User creates the Get request to view all morbidities 
    When User sends HTTPS with valid End point requets to get all morbidities
    Then User receives 200 ok status with response body of all the morbidities
    
  @tag2
  Scenario Outline: To verify thst user is getting 404 bad request with invalid End point.
   Given User creates the Get request to view all morbidities 
    When User sends HTTPS requets with invalid endpoint to get all morbidities
    Then User receives 404 NotFound status
    

