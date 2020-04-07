Feature: Login Page

  Scenario: Login as Lecturer
    Given Open 'LMS Login Page'
    Then Check the 'LMS Login page' has opened
    When Type login = 'testLecturer1' and password = 'testLecturer'
      And Click the 'Login' button
    Then Check the 'Lms Main Page' has opened