Feature: Login Page
  Scenario: Login as Lecturer
    Given Open 'LMS Login Page'
    When Type login = 'testLecturer' and password = 'testLecturer'
      And Click the 'Login' button
    Then Check the 'Lms Main Page' had opened