@unit-test @web-ui
Feature: Basic Tests
  Tests to ensure elements work as they are supposed to

  Background:
    Given user navigates to the login page
    And user provides valid credentials
    And user navigates to the "Files" module

  Scenario: Click select all files
    When user clicks on the select all files button
    Then wait for 10 seconds then close