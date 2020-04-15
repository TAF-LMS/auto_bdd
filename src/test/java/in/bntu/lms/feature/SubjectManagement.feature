Feature: Subject Management

  Scenario: Open the 'Subject' tab
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
    Then Check the 'Subject Form' has opened
      And Assert all

  Scenario: Edit a subject
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Click the 'Subject management' button
      And Click the 'Edit subject' button for the subject = 'test1'
      And Change the subject name to 'test12' and short subject name to 'tst12' and color to '#ff0000'
      And Unchecked the module: 'COMPLEX_MATERIAL'
      And Add the group: '10701117'
      And Save subject info
    Then Check subject table has subject:
      | subjectName | shortSubjectName |
      | test12      | tst12            |
      And Check subject table doesn't have the subject:
        | subjectName | shortSubjectName |
        | test1       | tst1             |
    When Click the 'Edit subject' button for the subject = 'test12'
    Then Check subject info:
      | subjectName | shortSubjectName | modules                                                                   | hex     | groups                      |
      | test12      | tst12            | NEWS,LECTURES,LABS,YE_MANAGEMENT,SUBJECT_ATTACHMENTS,SMART_TEST,PRACTICAL | #ff0000 | Тестовая,Резервная,10701117 |
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