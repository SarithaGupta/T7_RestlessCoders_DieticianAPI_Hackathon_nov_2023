@TRC-24
Feature: Retrieve Patient File By FileId

Scenario: Check if user able to retrieve patient File with valid File Id
Given User creates GET Request for the dietician API endpoint
When User sends HTTPS Request to fetch the patient File by valid fileId
Then User receives 200 OK Status with corresponding patient File