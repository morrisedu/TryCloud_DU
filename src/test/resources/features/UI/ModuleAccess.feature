@test @web-ui @nc
Feature: All modules access
  This feature tests whether the user has access to all modules of the app

  Scenario Outline: User has access to all main modules of the app
    Given user navigates to the login page
    When user provides valid credentials
    Then user should have access to the following "<modules>"
    Examples:
      |   modules   |
      |   Files     |
      |   Dashboard |
      |   Photos    |
      |   Talk      |
      |   Contacts  |
      |   Circles   |
      |   Calendar  |
      |   Desk      |
      |   Activity  |