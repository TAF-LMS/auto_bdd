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