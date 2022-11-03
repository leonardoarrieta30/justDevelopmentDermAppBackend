Feature: Patient Adding
  As a Developer
  I want to add Patient through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http:localhost:%d/api/v1/patients" is available

  @patient-adding
  Scenario: Add Patient
    When A post Request is sent with values "Jose Mariategui", 50, "12345678", "Anywhere", "This is a Description"
    Then A Response is received with Status 200
    And A Patient Resource is included in Response Body, with values "John Doe", 36, "123456789", "Anywhere", "This is a Description"

  @patient-duplicated
  Scenario: Add Patient with existing Name
    Given A Patient Resource with values "Jose Mariategui", 50, "12345678", "Anywhere", "This is a Description" is already stored
    When A post Request is sent with values "John Doe", 36, "123456789", "Anywhere", "This is a Description"
    Then A Response is received with Status 400
    And A Message is included in Response Body, with values "Not all constraints satisfied for Patient: A Patient with the same name already exists."