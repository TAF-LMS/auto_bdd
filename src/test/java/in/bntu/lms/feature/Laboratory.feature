@laboratory
Feature: Laboratory

  Scenario Outline: Add Laboratory
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Laboratory' button
      And Click the 'Add new Laboratory' button
      And Type laboratory information:
        | name   | shortName   | number   | duration   |
        | <name> | <shortName> | <number> | <duration> |
      And Save Laboratory
    Then Check laboratory table has the laboratory:
      | name   | shortName   | number   | duration   |
      | <name> | <shortName> | <number> | <duration> |
      And Assert all
    Examples:
      | name | shortName | number | duration |
      | labs | lbs1      | 1      | 4        |
      | lab1 | lb1       | 1      | 4        |
      | lab2 | lb2       | 1      | 4        |

  Scenario: Edit Laboratory
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
    And Open the 'Тестовый предмет' subject
    And Click the 'Laboratory' button
    And Edit the 'labs' laboratory
    And Type laboratory information:
      | name   | shortName   | number   | duration   |
      | labsN  | lbsN        | 2        | 8          |
    And Save Laboratory
    Then Check laboratory table has the laboratory:
      | name   | shortName   | number   | duration   |
      | labsN  | lbsN        | 2        | 8          |
      And Check laboratory table doesn't have the laboratory:
        | name   | shortName   | number   | duration   |
        | labs   | lbs         | 1        | 4          |
    And Assert all

  Scenario: Add mark for Laboratory
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Laboratory' button
      And Click the 'Result' button
      And Open edit mark for '1' student in the 'ЛР1' laboratory
      And Type mark value '6'
      And Save mark
    Then Check mark = '6' for '1' student in the 'ЛР1' laboratory
    When Open edit mark for '1' student in the 'ЛР1' laboratory
      And Type mark value '8'
      And Save mark
    Then Check mark = '8' for '1' student in the 'ЛР1' laboratory
    And Assert all

  Scenario: Create laboratory with file
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Laboratory' button
      And Click the 'Add new Laboratory' button
      And Type laboratory information:
        | name   | shortName   | number   | duration   |
        | labF   | lbF         | 1        | 4          |
      And Upload the file 'laboratory.png' for laboratory
      And Save Laboratory
      And Click the 'Files' button
    Then Check file 'laboratory.png' is present in the files page
    When Click the 'Laboratory' button
      And Remove the 'labF' laboratory
      And Accept the action
    Then Assert all


  Scenario Outline: Remove Laboratory
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Laboratory' button
      And Remove the '<name>' laboratory
      And Accept the action
    Then Check laboratory table doesn't have the laboratory:
      | name   | shortName   | number   | duration   |
      | <name> | <shortName> | <number> | <duration> |
      And Assert all
    Examples:
      | name  | shortName | number | duration |
      | labsN | lbsN      | 2      | 8        |
      | lab1  | lb1       | 1      | 4        |
      | lab2  | lb2       | 1      | 4        |