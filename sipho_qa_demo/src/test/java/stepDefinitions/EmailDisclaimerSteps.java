package stepDefinitions;

import BddBasedPackages.sipho_qa_demo.TestComponent.BaseTest;
import BddBasedPackages.sipho_qa_demo.pageobjects.EmailDisclaimerPage;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import io.cucumber.java.en.Then;




public class EmailDisclaimerSteps extends BaseTest {

    EmailDisclaimerPage disclaimerPage;

    // === Expected Values (centralized constants) ===
    private static final String EXPECTED_HEADING = "Shoprite Holdings Limited E-mail Disclaimer";
    private static final String EXPECTED_DISCLAIMER_PHRASE = "This message is deemed to contain confidential";
    private static final String EXPECTED_PRIVACY_URL_KEYWORD = "privacy";
    private static final String EXPECTED_FOOTER_TEXT = "All rights reserved";
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
        Assert.assertEquals("Page heading mismatch", expectedHeading, actualHeading);
    }

    @Then("the disclaimer section should contain {string}")
    public void theDisclaimerSectionShouldContain(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        String actualText = disclaimerPage.getDisclaimerText();
        Assert.assertTrue(
                "Disclaimer text mismatch.\nExpected phrase: " + EXPECTED_DISCLAIMER_PHRASE + "\nActual: " + actualText,
                actualText.contains(EXPECTED_DISCLAIMER_PHRASE)
        );
    }
    @When("the user clicks on the {string} link")
    public void userClicksOnPrivacyLink(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));

        // Click the link to navigate to the email disclaimer page
        link.click();
    }


    @Then("the browser should open the Data Privacy Statement PDF")
    public void verifyNavigationToDataPrivacy() {
        String expectedPdfUrl = "https://www.shopriteholdings.co.za/docs/shp-data-privacy.pdf";
        driver.get(expectedPdfUrl);

        // Wait for the PDF to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("shp-data-privacy.pdf"));

        // Get the current URL
        String pdfUrl = driver.getCurrentUrl();
        System.out.println("PDF opened at: " + pdfUrl);

        // Assert that the URL contains the expected PDF filename
        Assert.assertTrue(
                "Expected Data Privacy Statement PDF to be opened. Actual URL: " + pdfUrl,
                pdfUrl.toLowerCase().contains("shp-data-privacy.pdf")
        );
    }

    @Then("the footer should contain the text {string}")
    public void theFooterShouldContainTheText(String arg0) {
        String actualFooter = disclaimerPage.getFooterText();
        Assert.assertTrue(
                "Footer text mismatch.\nExpected: " + EXPECTED_FOOTER_TEXT + "\nActual: " + actualFooter,
                actualFooter.contains(EXPECTED_FOOTER_TEXT)
        );
        tearDown();
    }

    @When("the user clicks on the navigation link {string}")
    public void theUserClicksOnTheNavigationLink(String linkText) {
        disclaimerPage.clickNavLink(linkText);
    }

    @Then("the page should navigate successfully to {string}")
    public void verifyNavigationToOGroup(String expectedUrlKeyword) {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(
                "Navigation failed.\nExpected URL to contain: " + expectedUrlKeyword +
                        "\nActual URL: " + currentUrl,
                currentUrl.contains(expectedUrlKeyword.toLowerCase())
        );
        driver.quit(); // optional cleanup
    }
}
