@TRC-25
Feature: User Login
  I want to use this template for my feature file

  @tag1
  Scenario: User successfully logs in with correct credential
    Given User has correct email id and password
    When The user makes a login request using POST method
    Then API should respond with status code is "200" 
    And Return an access token
    And Assign "Dietician" role