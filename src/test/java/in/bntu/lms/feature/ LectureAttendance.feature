@attendance
Feature: Lecture Attendance

  Scenario: Marking students present/absent in the lecture
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Lectures' button
      And Click the 'Lecture Attendance' button
      And Select the 'Резервная' group
      And Click the 'Schedule Management' button
      And Click the 'Add' date and close modal page
    Then Check that NowDate was added on table
    When Click the 'Show Attendance' button
      And Add '2' h. for the '1' student
      And Save Lecture Attendance changes
    Then Check that '1' student has '2' h. on today
      And Assert all

  Scenario: Remove date from Lecture Attendance table
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'Lectures' button
      And Click the 'Lecture Attendance' button
      And Select the 'Резервная' group
      And Remove all dates
      And Accept the action
    Then Check that all dates were deleted from the table
      And Assert all