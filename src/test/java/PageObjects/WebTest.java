package PageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTest {
    WebDriver driver = null;
    public String browser;

    public WebTest(String browser) {
        this.browser = browser;
    }
    public void navigateToUrl() {
        switch (browser) {

            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = WebDriverManager.chromedriver().capabilities(options).create();
               //local driver
                // System.setProperty("webdriver.chrome.driver", ".\\src\\test\\java\\Drivers\\chromedriver.exe");
               // WebDriver driver = new ChromeDriver();
                break;

            case "FF":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                driver = null;
                break;
        }
        driver.get("https://www.mercedes-benz.co.uk");
        driver.manage().window().maximize();



    }

    public void selectCarModel() throws Exception {
        List<WebElement> lstElem = driver.findElements(By.tagName("cmm-cookie-banner"));

        for (WebElement webElement : lstElem) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(1000);
            SearchContext context = webElement.getShadowRoot();
            WebElement agreeBtn = context.findElement(By.cssSelector("cmm-buttons-wrapper.hydrated button[data-test='handle-accept-all-button']"));//locatoR good!
            agreeBtn.click();
            Thread.sleep(1000);
        }

        //Our Models Click
        WebElement root = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("owc-header")));
        SearchContext shadowDom = root.getShadowRoot();
        WebElement ourModelsBtn = shadowDom.findElement(By.cssSelector("ul[label='Menubar'] button:first-of-type"));
        ourModelsBtn.click();
        Thread.sleep(1000);

        //Hatchbacks click
        WebElement root2 = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("vmos-flyout")));
        SearchContext context2 = root2.getShadowRoot();
        WebElement hatchbackOption = context2.findElement(By.cssSelector("li[role='menuitem']:nth-child(3) li[role='menuitem']:nth-child(4) > div"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", hatchbackOption);
        hatchbackOption.click();
        Thread.sleep(1000);

        //A-Class Hatchback element
       // WebElement aClass = context2.findElement(By.cssSelector("div#app-vue owc-header-flyout ul[slot='seamless-vmos-flyout']")); //si
       WebElement aClass = context2.findElement(By.cssSelector("div#app-vue owc-header-flyout ul[slot='seamless-vmos-flyout'] a[href='https://www.mercedes-benz.co.uk/passengercars/models/hatchback/a-class/overview.html']")); //si
       aClass.click();

        //Build your car
        WebElement root3 = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("owc-stage")));
        SearchContext shadowDom2 = root3.getShadowRoot();
        WebElement buildYourCarBtn = shadowDom2.findElement(By.cssSelector("a.owc-stage-cta-buttons__button.wb-button.wb-button--medium.wb-button--theme-dark.wb-button--large.wb-button--secondary.owc-stage-cta-buttons__button--secondary"));//no
        buildYourCarBtn.click();
        Thread.sleep(1000);

        //Fuel Type dropdown
        WebElement root4 = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("owcc-car-configurator")));
        SearchContext shadowDom4 = root4.getShadowRoot();

        WebElement onlineText = shadowDom4.findElement(By.cssSelector("cc-motorization"));
        js.executeScript("arguments[0].scrollIntoView();", onlineText);
        WebElement root5 = shadowDom4.findElement(By.cssSelector("ccwb-multi-select"));

        SearchContext shadowDom5 = root5.getShadowRoot();
        WebElement fuelType = shadowDom5.findElement(By.cssSelector("button.button[aria-label='Fuel type, selected 0 items']"));
        Thread.sleep(1000);
        fuelType.click();
        Thread.sleep(1000);

        //Diesel option
        WebElement diesel = shadowDom4.findElement(By.cssSelector("ccwb-checkbox:first-of-type"));
        SearchContext dieselShadowDom = diesel.getShadowRoot();

        WebElement dieselInput = dieselShadowDom.findElement(By.cssSelector("ccwb-icon"));
        SearchContext dieselCheckboxShadowDom = dieselInput.getShadowRoot();
        WebElement dieselCheckbox = dieselCheckboxShadowDom.findElement(By.cssSelector("svg"));
        Actions action = new Actions(driver);
        action.click(dieselCheckbox).build().perform();
        Thread.sleep(1000);

        //Save the value “£” of the highest and lowest price results in a text file

    }

}
