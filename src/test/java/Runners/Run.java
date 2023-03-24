package Runners;

import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/java/Features/test.feature",
        glue = {"StepDefinitions"}
)
public class Run extends AbstractTestNGCucumberTests {

    public final static ThreadLocal<String> BROWSER = new ThreadLocal<>();

    @BeforeTest
    @Parameters({"Browser"})
    public void defineBrowser(String browser) {
        Run.BROWSER.set(browser);
        System.out.println(browser);
    }

}
