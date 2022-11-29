Feature: User can add service
  As a user,
  I want to create new services

  Scenario Outline: Create valid service
    Given the user is on / page
    When he enters "<url>" as url
    And he enters "<code>" as code
    And he enters "<message>" as message
    And he click on "Create/Update" button
    Then ensure the service added with url "<url>", message "<message>" and code <code>

    Examples:
      | url         | code | message |
      | /test       | 200  | ok      |
      | /other/test | 400  | deny    |

  Scenario: Create invalid service
    Given the user is on / page
    When he enters "/test/" as url
    And he enters "200" as code
    And he enters "text" as message
    And he click on "Create/Update" button
    Then ensure the service added with url "/test", message "test" and code 200