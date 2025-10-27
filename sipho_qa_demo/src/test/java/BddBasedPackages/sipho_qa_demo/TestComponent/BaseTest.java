package BddBasedPackages.sipho_qa_demo.TestComponent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.Before;
import org.junit.After;
import BddBasedPackages.sipho_qa_demo.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();

        // Load properties file
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("GlobalData.properties")) {
            if (inputStream == null) {
                throw new IOException("Property file 'GlobalData.properties' not found in classpath");
            }
            prop.load(inputStream);
        }

        // Get browser name - first from Maven command, fallback to properties file
        String browserName = System.getProperty("browser", prop.getProperty("browser"));

        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name not provided (via -Dbrowser or GlobalData.properties)");
        }


        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new org.openqa.selenium.firefox.FirefoxDriver();
//                break;

            default:
                throw new UnsupportedOperationException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        return driver;
    }

    @Before
    public void launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.gotTo();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
