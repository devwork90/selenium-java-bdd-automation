package BddBasedPackages.sipho_qa_demo.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailDisclaimerPage {

    private WebDriver driver;

    // === Locators ===
    private By pageHeading = By.tagName("h1");
    private By disclaimerText = By.xpath("//p[contains(text(),'1) This message is deemed to contain confidential')]"); // fallback for any main text container
    private By footerText = By.cssSelector("footer, .footer, .site-footer");
    private By dataPrivacyLink = By.xpath("//a[normalize-space()='data privacy statement']");
//    private By navLink = By.xpath("//a[@class='cmp-navigation__item-link'][normalize-space()='Our Group']");

    // === Constructor ===
    public EmailDisclaimerPage(WebDriver driver) {
        this.driver = driver;
    }

    // === Page Actions ===

    /** Opens the Shoprite Holdings Email Disclaimer page */
    public void openDisclaimerPage() {
        driver.get("https://www.shopriteholdings.co.za/email-disclaimer.html");
        driver.manage().window().maximize();
    }

    /** Returns the page heading text */
    public String getHeading() {
        WebElement headingElement = driver.findElement(pageHeading);
        return headingElement.getText().trim();
    }

    /** Returns the full disclaimer text */
    public String getDisclaimerText() {
        WebElement disclaimerElement = driver.findElement(disclaimerText);
        return disclaimerElement.getText().trim();
    }

    /** Checks if the disclaimer text section is visible */
    public boolean isDisclaimerTextPresent() {
        return driver.findElement(disclaimerText).isDisplayed();
    }

    /** Clicks on the Data Privacy Statement link */
    public void clickDataPrivacyLink() {
        WebElement linkElement = driver.findElement(dataPrivacyLink);

        //Scroll the element into the middle of the viewport
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", linkElement);

        //Wait until the element is clickable (no overlays)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(linkElement));

        try {
            //Attempt a normal Selenium click
            linkElement.click();
        } catch (ElementClickInterceptedException e) {
            //If still intercepted, scroll slightly more and retry via JavaScript
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", linkElement);
        }
    }

    public void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }


    /** Returns the footer text */
    public String getFooterText() {
        WebElement footerElement = driver.findElement(footerText);
        return footerElement.getText().trim();
    }

    /** Clicks a navigation link by visible text (e.g., “Our Group”) using XPath */
    public void clickNavLink(String linkText) {
        // Build dynamic XPath based on the link text
        By dynamicNavLink = By.xpath("//a[@class='cmp-navigation__item-link'][normalize-space()='" + linkText + "']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(dynamicNavLink));
        linkElement.click();
    }
}
