Feature: Navigation
  # This feature tests the navigation feature

  Scenario: Navigation is displayed
    Given user navigates to the login page
    When user provides valid credentials
    Then the following links should be displayed
      |   Files     |
      |   Dashboard |
      |   Photos    |
      |   Talk      |
      |   Contacts  |

  Scenario Outline: Navigation
    Given user navigates to the login page
    When user provides valid credentials
    And user clicks on "<nav>"
    Then user should be on the "<nav>" page
    Examples:
      |   nav       |
      |   Files     |
      |   Dashboard |
      |   Photos    |
      |   Talk      |
      |   Contacts  |