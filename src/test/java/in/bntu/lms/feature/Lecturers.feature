@lecture
Feature: Lectures

  Scenario Outline: Add lecture
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Lectures' button
      And Click the 'Add Lecture' button
      And Type and save lecture information and upload the file 'lectureImage.jpeg':
        | name   | time   |
        | <name> | <time> |
    Then Check lecture table has lecture:
      | name   | time   |
      | <name> | <time> |
      And Assert all
    Examples:
      | name          | time |
      | test lecture  | 2    |
      | test lecture1 | 2    |
      | test lecture2 | 2    |

  Scenario: Edit lecture
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Lectures' button
      And Edit the 'test lecture' lecture
      And Type and save lecture information:
        | name          | time |
        | test lecture0 | 10   |
    Then Check lecture table doesn't have lecture:
      | name         | time |
      | test lecture | 2    |
      And Check lecture table has lecture:
        | name          | time |
        | test lecture0 | 10   |
      And Assert all

  Scenario Outline: Remove lecture
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Lectures' button
      And Remove the '<name>' lecture
      And Accept the action
    Then Check lecture table doesn't have lecture:
      | name   | time   |
      | <name> | <time> |
      And Assert all
    Examples:
      | name          | time |
      | test lecture0 | 4    |
      | test lecture1 | 2    |
      | test lecture2 | 2    |