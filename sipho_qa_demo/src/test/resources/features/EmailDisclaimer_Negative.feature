Feature: Email Disclaimer page - Negative scenarios
  In order to ensure the email-disclaimer page handles invalid or unexpected cases correctly
  As a QA engineer
  I want to verify that incorrect or broken behaviors are properly managed and reported.

  Background:
    Given the user opens the email disclaimer page

  @Negative @InvalidHeading
  Scenario: Verify incorrect page heading is not displayed
    When the page is loaded
    Then the page heading should not be "Shoprite Holdings Limited Data Protection Notice"

  @Negative @BrokenLink
  Scenario: Verify clicking a non-existent link does not cause navigation
    When the user clicks on a non-existent link "Invalid Link"
    Then the browser should remain on the Email Disclaimer page

  @Negative @MissingText
  Scenario: Verify disclaimer section does not contain irrelevant text
    When the page is loaded
    Then the disclaimer section should not contain "Welcome to our Shopping Portal"

  @Negative @BrokenPrivacyLink
  Scenario: Verify Data Privacy Statement link is not broken
    When the user clicks on the "data privacy statement" link
    Then the browser should not navigate to a 404 error page

  @Negative @EmptyFooter
  Scenario: Verify footer does not appear blank
    When the page is loaded
    Then the footer text should not be empty
