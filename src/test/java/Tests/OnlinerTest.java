package Tests;

import Pages.CatalogPage;
import Pages.MainPage;
import Pages.TVPage;
import onliner.bytesttask.Browser;
import onliner.bytesttask.locatorGLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.List;


public class OnlinerTest {
    @BeforeSuite
    public void initBrowser(){
        Browser.getInstance().SetUp();
    }
    locatorGLoader locatorsclass;

    {
        try {
            locatorsclass = new locatorGLoader().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public  void closeBrowser(){
        Browser.getInstance().teardown();
    }
    @BeforeTest
    public  void TestOnliner() throws CloneNotSupportedException {
        MainPage tv = new MainPage(Browser.getInstance().getDriver());
        tv.catalogLink(locatorsclass.getLocator("mainPage.menu.element")).click();
        Assert.assertEquals(Browser.getInstance().getDriver().getCurrentUrl(),locatorsclass.getLocator("catalog.url"));
        CatalogPage catalog = new CatalogPage(Browser.getInstance().getDriver());
        WebDriverWait wait =  new WebDriverWait(catalog.driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("catalog-navigation__title")));
        catalog.mainMenulink(locatorsclass.getLocator("catalog.section.electronics")).click();
        catalog.asideMenulink(locatorsclass.getLocator("catalog.section.tv/video")).click();
        catalog.dropMenulink(locatorsclass.getLocator("catalog.section.drop.tv")).click();
        Assert.assertEquals(Browser.getInstance().getDriver().getCurrentUrl(),locatorsclass.getLocator("tv.section.url"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("schema-filter__label")));
        TVPage tvpage = new TVPage(tv.driver);
        tvpage.filterMenulink(locatorsclass.getLocator("tv.brand.name")).click();
        WebElement we = tvpage.filterMenulink(locatorsclass.getLocator("tv.resolution"));
        ((JavascriptExecutor) tv.driver).executeScript("arguments[0].scrollIntoView(true);", we);
        we.click();
        Select minres = new Select(tvpage.dpodownresmin());
        ((JavascriptExecutor) tv.driver).executeScript("arguments[0].scrollIntoView(true);", minres);
        minres.selectByValue(locatorsclass.getLocator("tv.screensize.min"));
        Select maxres = new Select(tvpage.dpodownresmax());
        maxres.selectByValue(locatorsclass.getLocator("tv.screensize.max"));
        WebElement maxprice =tvpage.maxprice();
        ((JavascriptExecutor) tv.driver).executeScript("arguments[0].scrollIntoView(true);", maxprice);
        maxprice.sendKeys(locatorsclass.getLocator("tv.price.max"));
    }

    @Test
    public void Resultscheck () throws CloneNotSupportedException {
        TVPage tv = new TVPage(Browser.getInstance().getDriver());
        WebDriverWait wait = new WebDriverWait(tv.driver, 15);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(tv.driver.findElement(By.className("schema-product__price")))));
        List<WebElement> BrandandType = tv.driver.findElements(By.xpath("//span[@data-bind = 'html: product.extended_name || product.full_name']"));
        for (int i = 0; i < BrandandType.size(); i++) {
            WebElement webElement = BrandandType.get(i);
            Assert.assertTrue(webElement.getText().contains(locatorsclass.getLocator("tv.brand.name.verify")));
        }
            List<WebElement> BrandandPrice = tv.driver.findElements(By.className("schema-product__price"));
            for (int i = 0; i < BrandandPrice.size(); i++) {
                WebElement webElement = BrandandPrice.get(i);
                String resol = webElement.getText();
                String sizeres[] = resol.split(" ");
                double price = Double.parseDouble(sizeres[1].replace(",","."));
                Assert.assertTrue(price <= 1000.00);
            }

        List<WebElement> Screensize = tv.driver.findElements(By.className("schema-product__description"));
        for (int i = 0; i < Screensize.size(); i++) {
            WebElement webElement = Screensize.get(i);
            String screensize = webElement.getText();
            String sizeres = screensize.substring(0, screensize.indexOf("\""));
            double size = Double.parseDouble(sizeres);
            double min = Double.parseDouble(locatorsclass.getLocator("tv.screensize.min")) / 10.0;
            double max = Double.parseDouble(locatorsclass.getLocator("tv.screensize.max")) / 10.0;
            Assert.assertTrue(min <= size && max >= size);
        }
        List<WebElement> ResolutionandSize = tv.driver.findElements(By.className("schema-product__description"));
        for (int i = 0; i < ResolutionandSize.size(); i++) {
            WebElement webElement = ResolutionandSize.get(i);
            Assert.assertTrue(webElement.getText().contains(locatorsclass.getLocator("tv.resolution")));
        }
    }
}