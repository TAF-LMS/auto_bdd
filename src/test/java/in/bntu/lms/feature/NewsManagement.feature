Feature: News Management

  Scenario Outline: Add news
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'News' button
      And Click the 'Add News' button
      And Type and save news information:
        | name       | text       |
        | <nameNews> | <textNews> |
    Then Check news table has news:
      | name       | text       |
      | <nameNews> | <textNews> |
      And Assert all
    Examples:
      | nameNews         | textNews              |
      | dataDriven news1 | dataDriven news text1 |
      | dataDriven news2 | dataDriven news text2 |
      | dataDriven news3 | dataDriven news text3 |

  Scenario: Hiding and displaying news
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'News' button
      And Click the 'Show All News' button
    Then Check the information message: 'Все новости активны' has displayed
      And Click the 'Hide All News' button
    Then Check the information message: 'Новости успешно скрыты' has displayed
      And Assert all

  Scenario Outline: Remove news
    Given Login in 'LMS' as users::lecturer
    When Click the 'Subjects' button
      And Open the 'Тестовый предмет' subject
      And Click the 'News' button
      And Delete the '<nameNews>' news
      And Accept the action
    Then Check news table doesn't have the news:
      | name       | text       |
      | <nameNews> | <textNews> |
      And Assert all
    Examples:
      | nameNews         | textNews              |
      | dataDriven news1 | dataDriven news text1 |
      | dataDriven news2 | dataDriven news text2 |
      | dataDriven news3 | dataDriven news text3 |