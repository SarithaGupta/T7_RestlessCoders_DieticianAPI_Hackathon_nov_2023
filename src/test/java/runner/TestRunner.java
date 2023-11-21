package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/userLogin/userLogin.feature",
        glue = {"com.dietician.backend.stepdefs"},
       // tags = "@EndtoEnd",
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-html-report", "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7"
                        + "Jvm"}
)
public class TestRunner {
}
