package nz.co.gps.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/nz/co/gps/features", // Specify the path to your feature files
        glue = {"nz.co.gps.steps"}, // Specify the package containing your step definitions
        plugin = {"pretty", "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json",
                "junit:target/cucumber-report/cucumber.xml"} // Specify desired plugins and output directory
        )

public class TestRunner {
    // This class will not have any code, it's just a runner class.
}