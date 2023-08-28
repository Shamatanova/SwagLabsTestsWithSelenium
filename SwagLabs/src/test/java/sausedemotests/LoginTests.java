package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test
    public void userAuthenticated_when_validCredentialsProvided(){
        //Assert user is log in

        WebElement pageTitle = driver.findElement(By.xpath("//div[@class='app_logo']"));
        Assertions.assertEquals("Swag Labs", pageTitle.getText(), "Page title is different than expected.");
        Assertions.assertTrue(pageTitle.isDisplayed(), "Page title is not visible.");
    }
}
