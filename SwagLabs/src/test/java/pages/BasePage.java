package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author DanielaShamatanova
 */
public class BasePage {
    final WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(WebElement element){
        element.click();
    }
}
