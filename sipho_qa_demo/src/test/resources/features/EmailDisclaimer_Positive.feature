Feature: Email Disclaimer page – Positive scenarios
  In order to ensure the email-disclaimer page functions correctly
  As a user
  I want to verify correct behaviour of the page under normal conditions.

  Background:
    Given the user opens the email disclaimer page

  Scenario: Verify page heading is displayed correctly
    When the page is loaded
    Then the page heading should be "Shoprite Holdings Limited E-mail Disclaimer"

  Scenario: Verify disclaimer text is visible
    Then the disclaimer section should contain "This message is deemed to contain confidential"

  Scenario: Verify Data Privacy Statement link is clickable
    When the user clicks on the "data privacy statement" link
    Then the browser should navigate to the Data Privacy Statement page

  Scenario: Verify footer text is displayed
    Then the footer should contain the text "All rights reserved"

  Scenario: Verify navigation menu link “Our Group” is clickable
    When the user clicks on the navigation link "Our Group"
    Then the page should navigate successfully to the Our Group section
