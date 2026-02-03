@regression
Feature: Login Functionality
  As a user of the application
  I want to be able to login
  So that I can access my account

  Background:
    Given user is on the login page

  @smoke @positive
  Scenario: Successful login with valid credentials
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should be redirected to products page
    And user should see the products page title

  @negative
  Scenario: Login with invalid username
    When user enters username "invalid_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"

  @negative
  Scenario: Login with invalid password
    When user enters username "standard_user"
    And user enters password "wrong_password"
    And user clicks on login button
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"

  @negative
  Scenario: Login with empty credentials
    When user clicks on login button
    Then user should see error message "Epic sadface: Username is required"

  @datadriven
  Scenario Outline: Login with multiple users
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks on login button
    Then user should see "<result>"

    Examples:
      | username        | password     | result  |
      | standard_user   | secret_sauce | success |
      | locked_out_user | secret_sauce | locked  |
      | problem_user    | secret_sauce | success |
