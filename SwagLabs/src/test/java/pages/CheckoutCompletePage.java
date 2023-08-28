package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author DanielaShamatanova
 */
public class CheckoutCompletePage extends BasePage{

    @FindBy(className = "title")
    private static WebElement title;

    @FindBy(className = "complete-header")
    private static WebElement completeMessage;

    @FindBy(className = "complete-text")
    private static WebElement completeText;

    @FindBy(id = "back-to-products")
    private static WebElement backButton;

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean titleIsDisplayed()
    {
        return title.isDisplayed();
    }

    public boolean completeHeaderIsDisplayed()
    {
        return completeMessage.isDisplayed();
    }
    public boolean completeTextIsDisplayed()
    {
        return completeText.isDisplayed();
    }
    public boolean backButtonIsDisplayed()
    {
        return backButton.isDisplayed();
    }
    public void clickBackButton()
    {
        backButton.click();
    }
}
