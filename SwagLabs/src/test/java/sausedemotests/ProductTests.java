package sausedemotests;

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.example.Constants.PRODUCTS_TO_ORDER;

/**
 * @author DanielaShamatanova
 */
public class ProductTests extends BaseTest {


    @BeforeEach
    public void resetInformationInShoppingCart_when_clickResetButton() {

        WebElement menuButton = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        menuButton.click();
        WebElement resetButton = driver.findElement(By.xpath("//a[@id='reset_sidebar_link']"));
        resetButton.click();
        verifyAllProductButtonsCanAddToCart();
        WebElement hideMenu = driver.findElement(By.xpath("//button[@id='react-burger-cross-btn']"));
        hideMenu.click();
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart() {

        //Add Backpack and T-shirt to shopping cart
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bike Light";

        addProductsInShoppingCartViaTitle(backpackTitle, shirtTitle);

        //assert number of products in the shopping cart
        int productsInShoppingCart = Integer.parseInt(driver.findElement
                (By.xpath("//span[@class='shopping_cart_badge']")).getText());
        Assertions.assertEquals(PRODUCTS_TO_ORDER,productsInShoppingCart,
                "Products in Shopping Cart are different than expected.");

        //assert items
        WebElement cartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartButton.click();
        WebElement checkoutButton = driver.findElement(By.xpath("//button[@id='checkout']"));
        Assertions.assertTrue(checkoutButton.isDisplayed(), "Checkout button is not visible.");

        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(PRODUCTS_TO_ORDER, items.size(),
                "Expected items in the shopping cart are different than actual.");

        Assertions.assertEquals(backpackTitle, items.get(0).getText(), "Item title is not than expected.");
        Assertions.assertEquals(shirtTitle, items.get(1).getText(), "Item title is not than expected.");
    }
}
