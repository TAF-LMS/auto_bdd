Feature: Login Page
  Scenario: Login as Lecturer
    Given Open 'LMS Login Page'
    When Type login = 'testLecture' and password = 'testLecture'
      And Click the 'Login' button
    Then Check the 'Lms Main Page' had opened