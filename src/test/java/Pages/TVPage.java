package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TVPage {
    public WebDriver driver;
    public TVPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;}
    private final String Filtermenu = "//input[@class= 'i-checkbox__real'][@value ='%s'] /following-sibling:: span";
    private final String resmin = "//div[@id='schema-filter']//div[1]/select[@class='schema-filter-control__item']";
    private final String resmax = "//div[@id='schema-filter']//div[2]/select[@class='schema-filter-control__item']";
    private final String price = "//div[@id='schema-filter']//div[2]/input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price']";

    public WebElement filterMenulink (String text){ return driver.findElement(By.xpath(String.format(Filtermenu,text)));}
    public WebElement dpodownresmin (){ return driver.findElement(By.xpath(String.format(resmin)));}
    public WebElement dpodownresmax (){ return driver.findElement(By.xpath(String.format(resmax)));}
    public WebElement maxprice (){ return driver.findElement(By.xpath(String.format(price)));}

}
