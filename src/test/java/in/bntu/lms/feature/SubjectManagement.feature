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
      | name        | shortName | module                         | color   | group             |
      | выш мат     | вм1       | NEWS                           | #FFDC00 | 10508113          |
#      | физкультура | фз1       | LECTURES,LABS,COMPLEX_MATERIAL | #FFFFFF | 10701114,10701115 |
#      | философия   | фс1       | PRACTICAL,YE_MANAGEMENT        | #FF0000 | 10701119          |

  Scenario: Edit the subject
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Click the 'Subject management' button
      And Click the 'Edit subject' button for the subject = 'физкультура'
      And Change the subject name to 'физкультура1' and short subject name to 'фз12' and color to '#ff0000'
      And Unchecked the module: 'COMPLEX_MATERIAL'
      And Add the group: '10701117'
      And Save subject info
    Then Check subject table has subject:
      | subjectName  | shortSubjectName |
      | физкультура1 | фз12             |
      And Check subject table doesn't have the subject:
        | subjectName | shortSubjectName |
        | физкультура | фз1             |
    When Click the 'Edit subject' button for the subject = 'физкультура1'
    Then Check subject info:
      | subjectName  | shortSubjectName | modules       | hex     | groups                     |
      | физкультура1 | фз12             | LECTURES,LABS | #ff0000 | 10701114,10701115,10701117 |
      And Assert all

  Scenario: Search the subject
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Click the 'Subject management' button
      And Type 'фи' in the subject filter
    Then Check that all subject contains 'фи' the filter value
      And Assert all

  Scenario: Join the lecturer to the subject
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Click the 'Lecturers' button
      And Join the 'testLecturer2 testLecturer2 testLecturer2' lecturer to the 'физкультура1' subject
    Then Check the lecturer table has lecturer 'testLecturer2 testLecturer2 testLecturer2'
    When Remove the 'testLecturer2 testLecturer2 testLecturer2' lecturer
    Then Check the lecturer table doesn't have lecturer 'testLecturer2 testLecturer2 testLecturer2'
      And Assert all

  Scenario Outline: Remove the subject
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Click the 'Subject management' button
      And Delete the '<subjectName>' subject
      And Accept the action
    Then Check subject table doesn't have the subject:
      | subjectName   | shortSubjectName   |
      | <subjectName> | <shortSubjectName> |
      And Assert all
    Examples:
      | subjectName  | shortSubjectName |
      | выш мат      | вм1              |
      | физкультура1 | фз12             |
      | философия    | фс2              |