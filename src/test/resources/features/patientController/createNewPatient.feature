@tag
Feature: POST: Verify the Create new patient

  @tag1
  Scenario Outline: POST: Create new Patient
    Given User needs to create a Patient using "<endpoint>"
    When User has the patientInfo from <rowNum> of "<sheet>"
    Then user hit the post method
    Then Verify the status code is "<StatusCode>"

    Examples:
      | endpoint | rowNum | sheet       | StatusCode |
      | patient  | 0      | PatientInfo | 201        |

  @tag2
  Scenario Outline: POST: Create new Patient with file attached
    Given User needs to create a Patient using "<endpoint>"
    When User has the patientInfo from <rowNum> of "<sheet>"
    Then user hit the post method and attach file
    Then Verify the status code is "<StatusCode>"

    Examples:
      | endpoint | rowNum | sheet       | StatusCode |
      | patient  | 1      | PatientInfo | 201        |

#  @tag3
#  Scenario Outline: POST: Create new Patient duplicate values with file attached
#    Given User needs to create a Patient using "<endpoint>"
#    When User has the patientInfo from <rowNum> of "<sheet>"
#    Then user hit the post method and attach file
#    Then Verify the status code is "<StatusCode>"
#
#    Examples:
#      | endpoint | rowNum | sheet       | StatusCode |
#      | patient  | 0      | PatientInfo | 400        |