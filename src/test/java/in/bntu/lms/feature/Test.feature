Feature: some feature
  Scenario: Some scenario
    Given Open 'LMS Login Page'
    When Type login = 'test' and password = 'test'
      And Click the 'Login' button
    Then Check page has opened