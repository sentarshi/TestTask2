package Pages;

 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;
    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    //Navigation menu parameter templeate
    private final String myLocator = "//span[@class = 'b-main-navigation__text' and text() = '%s']";
    public WebElement catalogLink (String text){
        return driver.findElement(By.xpath(String.format(myLocator,text)));
    }



}
