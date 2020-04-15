Feature: Subject Management

  Scenario: Open the 'Subject' tab
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
    Then Check the 'Subject Form' has opened
    And Assert all

  Scenario Outline: Add a subject
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
    And Click the 'Subject management' button
    Then Check the 'Subject Management' page has opened
    When Click the 'Add Subject' button
    Then Check the 'Edit Subject' page has opened
    When Type and Save the subject information:
      | subjectName | shortSubjectName | modules  | hex     | groups  |
      | <name>      | <shortName>      | <module> | <color> | <group> |
    Then Check subject table has subject:
      | subjectName | shortSubjectName |
      | <name>      | <shortName>      |
    And Assert all


    Examples:
      | name        | shortName | module                  | color   | group             |
      | выш мат     | вм1       | NEWS                    | #FFDC00 | 10508113          |
      | физкультура | фз1       | LECTURES,LABS           | #FFFFFF | 10701114,10701115 |
      | философия   | фс1       | PRACTICAL,YE_MANAGEMENT | #FF0000 | 10701119          |