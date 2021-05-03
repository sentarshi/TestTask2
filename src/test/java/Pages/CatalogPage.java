package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {
    public WebDriver driver;
    public CatalogPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;}

    //Navigation menu parameter templeate
    private final String mainMenu = "//span[@class = 'catalog-navigation-classifier__item-title-wrapper' and text() = '%s']";
    //Navigation Side menu parameter template
    private final String asideMenu = "//div[@class='catalog-navigation-list__aside-title' and contains(text(), '%s')]";
    private final String dropMenu = "//span[@class='catalog-navigation-list__dropdown-title' and contains(text(), '%s')]";

    public WebElement mainMenulink (String text){
    return driver.findElement(By.xpath(String.format(mainMenu,text)));}
    public WebElement asideMenulink (String text){ return driver.findElement(By.xpath(String.format(asideMenu,text)));}
    public WebElement dropMenulink (String text){ return driver.findElement(By.xpath(String.format(dropMenu,text)));}

    }




