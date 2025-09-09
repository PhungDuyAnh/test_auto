Feature: Order Functionality

  Scenario: Place order with valid information
    Given I am logged in as "admin" with password "admin"
    When I add a product to cart and place an order with:
      | name    | country | city | card        | month | year |
      | DuyAnh  | VN      | HN   | 01230120321 | 09    | 2025 |
    Then The order should be successful

  Scenario: Place order with empty fields
    Given I am logged in as "admin" with password "admin"
    When I add a product to cart and place an order with:
      | name | country | city | card | month | year |
      |      |         |      |      |       |      |
    Then I should see an alert "Please fill out Name and Creditcard."

