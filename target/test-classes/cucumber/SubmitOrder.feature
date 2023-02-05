
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productsName> to cart
    And checkout <productsName> and submit the order
    Then "THANK YOU FOR YOUR ORDER" message is displayed in confirmation page

    Examples: 
      | name           | password         |      productsName   |
      | standard_user  | secret_sauce     | Sauce Labs Backpack |
      
