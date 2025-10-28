package stepDefinitions;

import BddBasedPackages.sipho_qa_demo.TestComponent.BaseTest;
import BddBasedPackages.sipho_qa_demo.pageobjects.EmailDisclaimerPage;

import io.cucumber.java.en.*;
import org.junit.Assert;
import java.io.IOException;


import io.cucumber.java.en.Then;




public class EmailDisclaimerSteps extends BaseTest {

    EmailDisclaimerPage disclaimerPage;

    // === Expected Values (centralized constants) ===
    private static final String EXPECTED_PRIVACY_URL_KEYWORD = "privacy";
    private static final String EXPECTED_NAVIGATION_LINK = "Our Group";
    private static final String EXPECTED_NAVIGATION_URL_KEYWORD = "group.html";


    // === Step Definitions ===

    @Given("the user opens the email disclaimer page")
    public void openDisclaimerPage() {
        try {
            driver = initializeDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        disclaimerPage = new EmailDisclaimerPage(driver);
        disclaimerPage.openDisclaimerPage();
    }

    @When("the page is loaded")
    public void pageIsLoaded() {
        String heading = disclaimerPage.getHeading();
        Assert.assertTrue("Page heading not found or empty", heading != null && !heading.isEmpty());
    }

    @Then("the page heading should be {string}")
    public void verifyPageHeading(String expectedHeading) {
        String actualHeading = disclaimerPage.getHeading();
        Assert.assertTrue(
                "Page heading mismatch.\nExpected: " + expectedHeading + "\nActual: " + actualHeading,
                actualHeading.contains(expectedHeading)
        );
    }

    @Then("the disclaimer section should contain {string}")
    public void verifyDisclaimerText(String expectedDisclaimer) {
        String actualText = disclaimerPage.getDisclaimerText();
        Assert.assertTrue(
                "Disclaimer text mismatch.\nExpected phrase: " + expectedDisclaimer + "\nActual: " + actualText,
                actualText.contains(expectedDisclaimer)
        );
    }


    @When("the user clicks on the {string} link")
    public void userClicksOnPrivacyLink(String linkText) {
        disclaimerPage.clickDataPrivacyLink();
    }

    @Then("the browser should navigate to the Data Privacy Statement page")
    public void verifyNavigationToDataPrivacy() {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(
                "Browser did not navigate to Data Privacy page.\nURL: " + currentUrl,
                currentUrl.contains(EXPECTED_PRIVACY_URL_KEYWORD)
        );
    }

    @Then("the footer should contain the text {string}")
    public void verifyFooterText(String expectedText) {
        String actualFooter = disclaimerPage.getFooterText();
        Assert.assertTrue(
                "Footer text mismatch.\nExpected: " + expectedText + "\nActual: " + actualFooter,
                actualFooter.contains(expectedText)
        );
    }

    @When("the user clicks on the navigation link {string}")
    public void clickNavigationLink(String LinkText) {
        disclaimerPage.clickNavLink(EXPECTED_NAVIGATION_LINK);
    }

    @Then("the page should navigate successfully to the Our Group section")
    public void verifyNavigationSuccess() {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(
                "Navigation failed.\nExpected URL to contain: " + EXPECTED_NAVIGATION_URL_KEYWORD +
                        "\nActual URL: " + currentUrl,
                currentUrl.contains(EXPECTED_NAVIGATION_URL_KEYWORD)
        );
    }

    @Then("the page heading should not be {string}")
    public void verifyIncorrectHeading(String incorrectHeading) {
        String actualHeading = disclaimerPage.getHeading();
        Assert.assertNotEquals("Unexpected page heading detected", incorrectHeading, actualHeading);
    }

    @When("the user clicks on a non-existent link {string}")
    public void clickNonExistentLink(String invalidLinkText) {
        try {
            disclaimerPage.clickLink(invalidLinkText);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Link not found as expected: " + invalidLinkText);
        }
    }

    @Then("the browser should remain on the Email Disclaimer page")
    public void verifyStillOnDisclaimerPage() {
        Assert.assertTrue("Unexpected page navigation occurred",
                disclaimerPage.getCurrentUrl().contains("email-disclaimer.html"));
    }

    @Then("the disclaimer section should not contain {string}")
    public void verifyDisclaimerDoesNotContain(String invalidText) {
        String disclaimer = disclaimerPage.getDisclaimerText();
        Assert.assertFalse("Irrelevant text found in disclaimer", disclaimer.contains(invalidText));
    }

    @Then("the browser should not navigate to a 404 error page")
    public void verifyNotNavigatedToErrorPage() {
        Assert.assertFalse("Navigated to a 404 error page unexpectedly",
                disclaimerPage.getCurrentUrl().contains("404"));
    }

    @Then("the footer text should not be empty")
    public void verifyFooterNotEmpty() {
        String footerText = disclaimerPage.getFooterText().trim();
        Assert.assertFalse("Footer text is unexpectedly empty", footerText.isEmpty());
    }


}
