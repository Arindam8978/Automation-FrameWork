
Feature: Error Validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Epic sadface: Username and password do not match any user in this service" Error message is displayed

    Examples: 
      | name           | password          |     
      | standard_user  | secret_sauces     | 
