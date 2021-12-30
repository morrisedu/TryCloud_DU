@test @web-ui
Feature: Login Functionality
  This feature tests the login functionality works as expected

  Background:
    Given user navigates to the login page

  Scenario: Login with correct credentials
    When user provides valid credentials
    Then the following links should be displayed
      |   Files     |
      |   Dashboard |
      |   Photos    |
      |   Talk      |
      |   Contacts  |

  Scenario Outline: Login with correct credentials, username: "<username>", password: "<password>"
    When user provides the correct "<username>" and the correct "<password>"
    Then the following links should be displayed
      |   Files     |
      |   Dashboard |
      |   Photos    |
      |   Talk      |
      |   Contacts  |
    Examples:
      | username  | password    |
      | User10    | Userpass123 |
      | User40    | Userpass123 |
      | User70    | Userpass123 |
      | User100   | Userpass123 |

  Scenario Outline: Login with wrong credentials, username: "<username>", password: "<password>"
    When user provides wrong "<username>" and the wrong "<password>"
    Then user should receive the message "Wrong username or password."
    And user should still be at the login page
    Examples:
      | username  | password    |
      | aUserName | Userpass123 |
      | hju       | kjuhsi      |
      | User100   | userpass123 |

  Scenario Outline: Login without entering one or both credentials, username: "<username>", password: "<password>"
    When user provides wrong "<username>" and the wrong "<password>"
    Then required field exception, "Please fill out this field.", should be displayed
    And user should still be at the login page
    Examples:
      | username  | password    |
      |           | Userpass123 |
      |           | kjuhsi      |
      | User100   |             |
      | User40    |             |

    #ToDo add security scenatio with SQL injection
