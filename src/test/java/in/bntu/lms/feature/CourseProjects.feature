@courseproject
Feature: Course Projects

  Scenario Outline: Add and edit course project name
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Course Project' button
      And Click the 'Course themes' button
      And Click the 'Add new course theme' button
      And Type '<name>' course name
      And Save course theme information
    Then Check Course Project Theme table has course project:
      | name   | studentFullName | group | date |
      | <name> |                 |       |      |
    When Click the 'Edit button' for the '<name>' course theme
      And Type '<name1>' course name
      And Save course theme information
    Then Check Course Project Theme table has course project:
      | name    | studentFullName | group | date |
      | <name1> |                 |       |      |
    When Click the 'Assign group' button for course theme '<name1>'
      And Assign '<studentFullName>' for course project
    Then Check Course Project Theme table has course project for current date:
      | name    | studentFullName   | group   | date |
      | <name1> | <studentFullName> | <group> |      |
      And Assert all
    Examples:
      | name       | name1       | studentFullName               | group     |
      | Test theme | Test theme1 | Protasevich Andrey -          | Резервная |
      | Test thm1  | Test them1  | yarosh2 yarosh2 yarosh2       | Резервная |
      | Test thm2  | Test them2  | student22 student22 student22 | Резервная |

  Scenario Outline: Remove course project name
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Course Project' button
      And Click the 'Course themes' button
      And Click the 'UnAssign student' button for course theme '<name>'
      And Accept the Deleted action
    Then Check Course Project Theme table has course project:
      | name   | studentFullName | group | date |
      | <name> |                 |       |      |
    When Click the 'Delete theme' button for course theme '<name>'
      And Accept the Deleted action
    Then Check Course Project Theme table doesn't have course project:
      | name   | studentFullName | group | date |
      | <name> |                 |       |      |
      And Assert all
    Examples:
      | name        |
      | Test theme1 |
      | Test them1  |
      | Test them2  |

  Scenario Outline: Add and Edit course percentage
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Course Project' button
      And Click the 'Course Percent Schedule' button
      And Click the 'Add New Percentage' button
      And Type the course percentage information:
        | name   | percent   | date   |
        | <name> | <percent> | <date> |
      And Save the course percentage information
    Then Check Course Percentage table has CoursePercentage:
      | name   | percent   | date   |
      | <name> | <percent> | <date> |
    When Click the 'Edit' button for '<name>' percentage
      And Type the course percentage information:
        | name      | percent      | date      |
        | <newName> | <newPercent> | <newDate> |
      And Save the course percentage information
    Then Check Course Percentage table has CoursePercentage:
      | name | percent | date       |
      | <newName> | <newPercent> | <newDate> |
      And Check Course Percentage table doesn't have CoursePercentage:
        | name   | percent   | date   |
        | <name> | <percent> | <date> |
      And Assert all
    Examples:
      | name   | newName | percent | newPercent| date       | newDate    |
      | perc20 | perc25  | 20      | 25        | 10/05/2020 | 11/05/2020 |
      | perc40 | perc50  | 40      | 50        | 02/05/2020 | 01/05/2020 |
      | perc60 | perc90  | 60      | 90        | 03/05/2020 | 04/06/2020 |

  Scenario Outline: Remove course percentage
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Course Project' button
      And Click the 'Course Percent Schedule' button
      And Click the 'Remove' button for '<name>' percentage
      And Accept the Deleted action
    Then Check Course Percentage table doesn't have CoursePercentage:
      | name   | percent   | date   |
      | <name> | <percent> | <date> |
      And Assert all
    Examples:
      | name   | percent | date       |
      | perc25 | 25      | 11/05/2020 |
      | perc50 | 50      | 01/04/2020 |
      | perc90 | 90      | 20/06/2020 |