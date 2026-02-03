@regression
Feature: Product Management
  As a logged in user
  I want to view and manage products
  So that I can add items to my cart

  Background:
    Given user is logged in with username "standard_user" and password "secret_sauce"

  @smoke
  Scenario: View products on products page
    When user is on products page
    Then user should see list of products
    And products should have name and price

  @cart
  Scenario: Add product to cart
    When user is on products page
    And user adds "Sauce Labs Backpack" to cart
    Then cart badge should show 1 item
    And user navigates to cart page
    And user should see "Sauce Labs Backpack" in cart

  @cart
  Scenario: Add multiple products to cart
    When user is on products page
    And user adds "Sauce Labs Backpack" to cart
    And user adds "Sauce Labs Bike Light" to cart
    Then cart badge should show 2 items

  @sort
  Scenario: Sort products by price low to high
    When user is on products page
    And user sorts products by "Price (low to high)"
    Then products should be sorted by price in ascending order
