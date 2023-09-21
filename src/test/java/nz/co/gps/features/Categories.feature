@category
Feature: This feature file covers tests related to the Category functionality of TMSandbox API

  Using the below TM Sandbox API this automated test covers the following acceptance criteria:
  API = https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false

  Acceptance Criteria:
  Name = "Carbon credits"
  CanRelist = true
  The Promotions element with Name = "Gallery" has a Description that contains the text "Good position in category"

  @acceptance
  Scenario: Validate all the 3 acceptance criteria in the response
    When I send a GET request with following inputs:
      | endpoint | /v1/Categories/6327/Details.json |
      | params   | catalogue::false                 |
    Then I see the response code is 200
    And I see the response contains the following data:
      | Name      | Carbon credits |
      | CanRelist | true           |
    And The Promotions element with Name as Gallery has a Description that contains the text Good position in category
