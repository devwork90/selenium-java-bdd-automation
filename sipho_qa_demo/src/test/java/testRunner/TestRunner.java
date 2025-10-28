package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        tags = "@Positive or @Negative",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html-report.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/CucumberTestReport.xml",
                "timeline:target/cucumber-reports/timeline"
        },
        monochrome = true,
        dryRun = false
)
public class TestRunner {
}
