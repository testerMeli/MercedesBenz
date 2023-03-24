package PageObjects;

import Runners.Run;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        WebElement root3 = context2.findElement(By.cssSelector("div#app-vue owc-header-flyout"));
        SearchContext context3 = root3.getShadowRoot();
        WebElement prueba1 = context3.findElement(By.cssSelector("ul:first-child"));
        Thread.sleep(1000);
       // js.executeScript("vmos-flyout-1.10.1.min.js", prueba1);
        //script seamless-vmos-flyout
        //vmos-flyout-1.10.1.min.js
        //WebElement slot = context3.findElement(By.cssSelector(".owc-header-flyout__navigation-list slot")); //si
        //WebElement element5 = (WebElement)  ((JavascriptExecutor) driver).executeScript("return arguments[0].click()",slot); //si
        //slot projection in static HTML
        //NEXT ELEMENT TO REACH
        //WebElement test = driver.findElement(By.cssSelector("a[class='@vmos-vmos-flyout-flyout-group-item__link__NeNLP'][href='https://www.mercedes-benz.co.uk/passengercars/models/hatchback/a-class/overview.html']"));

    }

}
