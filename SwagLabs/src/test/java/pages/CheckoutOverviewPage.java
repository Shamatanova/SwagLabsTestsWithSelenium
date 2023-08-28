package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author DanielaShamatanova
 */
public class CheckoutOverviewPage extends BasePage {
    public static final double VAT = 0.08;

    @FindBy(className = "title")
    private static WebElement title;

    @FindBy(className = "cart_item")
    private List<WebElement> productList;

    @FindBy(className = "summary_subtotal_label")
    private static WebElement itemTotalPrice;

    @FindBy(className = "summary_tax_label")
    private WebElement tax;

    @FindBy(id = "finish")
    private static WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean titleIsDisplayed() {
        return title.isDisplayed();
    }

    public boolean priceForItemsIsDisplayed() {
        return itemTotalPrice.isDisplayed();
    }

    public boolean finishButtonIsDisplayed() {
        return finishButton.isDisplayed();
    }

    public double sumForAllProductsInShoppingCart() {
        double sum = 0;
        for (var product : productList) {
            var priceInStrings = product.findElement(By.className("inventory_item_price")).getText();
            var price = Double.parseDouble(priceInStrings.substring(1));
            sum += price;
        }
        return sum;
    }

    public double calculateTotalPrice() {
        var price = sumForAllProductsInShoppingCart();
        return price * VAT;
    }

    public int numberOfProductsInShoppingCart()
    {
        return productList.size();
    }
}
