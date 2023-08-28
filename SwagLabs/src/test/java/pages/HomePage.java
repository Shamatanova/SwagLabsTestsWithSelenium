package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

/**
 * @author DanielaShamatanova
 */
public class HomePage extends BasePage {
    @FindBy(className = "title")
    private static WebElement title;

    @FindBy(className = "shopping_cart_link")
    private static WebElement shoppingCart;

    @FindBy(className = "cart_item")
    private static ArrayList<WebElement> products;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean titleIsDisplayed()
    {
        return title.isDisplayed();
    }
    public boolean productsAreDisplayed()
    {
        if (products.isEmpty())
        {
            return false;
        }
        return true;
    }

    public boolean shoppingCartIsVisible(){
        return shoppingCart.isDisplayed();
    }
}
