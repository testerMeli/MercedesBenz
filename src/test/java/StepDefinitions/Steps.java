package StepDefinitions;

import PageObjects.WebTest;
import Runners.Run;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class Steps {
    private WebTest webTest;
    static boolean prevScenarioFailed = false;
    @Before
    public void setUp() {
        if (prevScenarioFailed) {
            throw new IllegalStateException("Previous scenario failed!");
        }
        //get the browser value for current thread
        String browser = Run.BROWSER.get();
        System.out.println("WebAppStepDefinitions: " + browser);
        webTest = new WebTest(browser);
    }


    @Given("the user goes to the Mercedes website")
    public void the_user_goes_to_the_mercedes_website() {
        webTest.navigateToUrl();


    }
    @When("select the model {string}")
    public void select_the_model(String string) throws Exception {
        webTest.selectCarModel();
    }
    @When("Build your car")
    public void build_your_car() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webTest.buildYourCar();
    }
    @Then("find the prices list")
    public void find_the_prices_list() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        webTest.getCarLowPrice();
        webTest.getCarHighPrice();
    }


}
