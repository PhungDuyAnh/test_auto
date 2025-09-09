package demoblaze.bdd.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/demoblaze/bdd/features",
    glue = "demoblaze.bdd.stepdefs",
    plugin = {"pretty", "html:target/cucumber-report.html"},
    monochrome = true
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {
}

