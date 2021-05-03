package onliner.bytesttask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    private static Browser instance;

    public static  Browser getInstance() {
        if (instance == null) instance = new Browser();
        return instance;
    }

    private Browser() {

    }
    private WebDriver driver;

    public WebDriver SetUp() {
                System.setProperty("webdriver.chrome.driver", ConfigLoader.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(ConfigLoader.getProperty("websiteURL"));
        return driver;

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void teardown(){
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
