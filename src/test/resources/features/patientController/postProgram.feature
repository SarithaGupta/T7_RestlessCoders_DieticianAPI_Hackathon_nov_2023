
Feature: POST REQUEST
  Background: User sets Authorization to No  Auth

  Scenario: Check if user able to create a program with valid endpoint and request body (non existing values)
Given User creates POST Request for create program the LMS API endpoint
When User sends HTTPS Request and  request Body with program Name, program status, program Description
Then User receives 201 Created Status with response body
