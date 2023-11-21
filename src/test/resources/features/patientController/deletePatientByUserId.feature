
@tag
Feature: POST: Verify the Delete Patient by Id

  @tag2
  Scenario Outline: DELETE: Delete Patient
    Given User needs to delete a Patient using "<endpoint>"
    When User has the patientId from <rowNum> of "<sheet>"
    Then user calls the delete api for status "<StatusCode>"

    Examples:
      | endpoint | rowNum | sheet             | StatusCode |
      | patient  | 0      | deletePatientInfo | 200        |
