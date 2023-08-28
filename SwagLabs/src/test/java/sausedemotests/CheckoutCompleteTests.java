package sausedemotests;

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.HomePage;

import static org.example.Constants.*;

/**
 * @author DanielaShamatanova
 */
public class CheckoutCompleteTests extends BaseTest {

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
    public void orderCompleted_when_addProduct_and_checkout_withConfirm()
    {
        //Add Backpack and T-shirt to shopping cart
        var backpackTitle = "Sauce Labs Backpack";
        var shirtTitle = "Sauce Labs Bike Light";
        addProductsInShoppingCartViaTitle(backpackTitle, shirtTitle);

        //assert number of products in the shopping cart
        var productsInShoppingCart = Integer.parseInt(driver.findElement
                (By.xpath("//span[@class='shopping_cart_badge']")).getText());
        Assertions.assertEquals(PRODUCTS_TO_ORDER, productsInShoppingCart,
                "Products in Shopping Cart are different than expected.");

        //Click on shopping Cart and Checkout button
        var cartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartButton.click();
        var checkoutButton = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkoutButton.click();

        //Fill contact Details
        var checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutInformationPage.setFirstName(FIRST_NAME);
        checkoutInformationPage.setLastName(LAST_NAME);
        checkoutInformationPage.setZipCode(ZIP_CODE);
        checkoutInformationPage.clickContinueButton();

        // Assert Items removed from Shopping Cart
        var checkoutOverviewPage = new CheckoutOverviewPage(driver);
        WebElement finishButton = driver.findElement(By.xpath("//button[@id='finish']"));
        checkoutOverviewPage.clickButton(finishButton);

        var checkoutCompletePage = new CheckoutCompletePage(driver);
        Assertions.assertTrue(checkoutCompletePage.titleIsDisplayed(), "Page title is different than expected.");
        Assertions.assertTrue(checkoutCompletePage.completeHeaderIsDisplayed(), "Page header is different than expected.");
        Assertions.assertTrue(checkoutCompletePage.completeTextIsDisplayed(), "Complete message description is different than expected.");
        Assertions.assertTrue(checkoutCompletePage.backButtonIsDisplayed(), "Back button is not visible.");

        checkoutCompletePage.clickBackButton();

        var homePage = new HomePage(driver);
        var title = homePage.titleIsDisplayed();
        var products = homePage.productsAreDisplayed();
        var shoppingCart = homePage.shoppingCartIsVisible();

    }
}
