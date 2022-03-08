@test @web-ui @wip
Feature: Files Module
  User should be able to access the Files Module

  Background:
    Given user navigates to the login page
    And user provides valid credentials
    And user navigates to the "Files" module

  Scenario: User can access the files module
    Then user should be redirected to the files module page
    #ToDo on fixing Regex, add URL validation here

  Scenario: User can select all uploaded files from page
    When user clicks on the select all files button
    Then all uploaded files should be selected

  Scenario: User can add a file to favorites
    When user adds file to favorites
    And user goes to the "Favorites" menu
    Then the file should be added to favorites
    # ToDO fix getting files in favorite & all files present.

  Scenario: User can remove a file from favorites
    And user goes to the "Favorites" menu
    And there are files present in favorites
    When user removes a file from favorites
    Then the file should be removed from favorites
