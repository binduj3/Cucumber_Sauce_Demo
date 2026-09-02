@login @loginModule @regression
Feature: Login Page UI and Authentication Validation

  Background:
    Given user navigates to the SauceDemo login page

  @login @ui @smoke @high
  Scenario: Verify all core login elements are displayed
    Then user is able to see the "Username" input field
    And user is able to see the "Password" input field
    And user is able to see the "Login" button

  @login @ui @high
  Scenario: Verify Login button is enabled by default
    Then user is able to see that the "Login" button is enabled

  @login @positive @smoke @high
  Scenario: Successful login with valid standard user credentials
    When user enters "standard_user" into the Username field
    And user enters "secret_sauce" into the Password field
    And user clicks the Login button
    Then user is able to see the Products page

  @login @negative @high
  Scenario: Login attempt with empty username and empty password
    When user leaves the Username field empty
    And user leaves the Password field empty
    And user clicks the Login button
    Then user is not able to log in
    And user is able to see the error message "Epic sadface: Username is required"

  @login @errorhandling @high
  Scenario Outline: Login fails when a required field is left empty for <field>
    When user enters "<username>" into the Username field
    And user enters "<password>" into the Password field
    And user clicks the Login button
    Then user is not able to log in
    And user is able to see the error message "<errorMessage>"
    Examples:
      | username      | password      | field    | errorMessage                             |
      |               | secret_sauce  | username | Epic sadface: Username is required       |
      | standard_user |               | password | Epic sadface: Password is required       |


  @login @positive @excel @datadriven @medium
  Scenario: Login using credentials sourced from the Excel test data file
    When user logs in using credentials from the "LoginData" sheet in the Excel test data file
    Then user is able to see the Products page