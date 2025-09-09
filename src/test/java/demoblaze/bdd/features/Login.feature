Feature: Login Functionality

  Scenario Outline: Login with valid and invalid credentials
    Given I am on the home page
    When I click the login link
    And I login with username "<username>" and password "<password>"
    Then The login result should be "<result>"

    Examples:
      | username | password | result   |
      | admin    | admin    | success  |
      | user2    | wrongpass| failure  |
