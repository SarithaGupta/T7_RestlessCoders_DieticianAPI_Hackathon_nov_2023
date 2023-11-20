@tag
Feature: POST: Verify the Create new patient

  @tag2
  Scenario Outline: POST: Create new Patient
    Given User needs to create a Patient using "<endpoint>"
    When User has the patientInfo from <rowNum> of "<sheet>"
    Then user hit the post call the method
    Then Verify the status code is "<StatusCode>"

    Examples:
      | endpoint | rowNum | sheet       | StatusCode |
      | patient  | 0      | PatientInfo | 201        |