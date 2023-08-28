package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author DanielaShamatanova
 */
public class CheckoutInformationPage extends BasePage{

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement zipCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutInformationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName)
    {
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName)
    {
        lastNameField.sendKeys(lastName);
    }

    public void setZipCode(String zipCode)
    {
        zipCodeField.sendKeys(zipCode);
    }

    public void clickContinueButton()
    {
        continueButton.click();
    }
}
