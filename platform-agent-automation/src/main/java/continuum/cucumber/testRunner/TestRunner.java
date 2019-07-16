package continuum.cucumber.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src//test//java//feature", glue= {"stepDefinition"},
plugin = {
		"pretty",
		"html:test-report/cucumber",
		"json:test-report/cucumber.json",
        "rerun:target/rerun.txt" },
tags={"@shiv"},
dryRun = false
)
public class TestRunner {

}
