Feature: Dermatologist Adding
  As a Developer
  I want to add Dermatologist through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http:localhost:%d/api/v1/dermatologists" is available

  @dermatologist-adding
  Scenario: Add Dermatologist
    When A post Request is sent with values "John Doe", 36, "123456789", "Anywhere", "This is a Description"
    Then A Response is received with Status 200
    And A Dermatologist Resource is included in Response Body, with values "John Doe", 36, "123456789", "Anywhere", "This is a Description"