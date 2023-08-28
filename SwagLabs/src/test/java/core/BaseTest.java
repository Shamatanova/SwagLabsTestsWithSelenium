package core;

import org.example.BrowserTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.Constants.*;


public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeEach
    public void waitAllElements() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeAll
    public static void beforeTests() {

        driver = startBrowser(BrowserTypes.CHROME);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get(BASE_URL);

        authenticateWithUser(VALID_USERNAME, VALID_PASSWORD);
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }

    public static void authenticateWithUser(String user, String pass) {
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys(user);

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();

        WebElement inventoryPageTitle = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(inventoryPageTitle));
    }

    protected static WebDriver startBrowser(BrowserTypes browserType) {
        switch (browserType) {
            case EDGE:
                return new EdgeDriver();
            case EDGE_HEADLESS:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);
            case CHROME:
                return new ChromeDriver();
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                return new FirefoxDriver();
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }
    protected void addProductsInShoppingCartViaTitle(String... productTitles) {

        for (String title : productTitles) {
            WebElement product = getProductByTitle(title);
            product.findElement(By.className("btn_inventory")).click();
        }
    }
    protected WebElement getProductByTitle(String productTitles) {

        return driver.findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", productTitles)));
    }
    protected void verifyAllProductButtonsCanAddToCart() {

        List<WebElement> nodeList = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        for (var node : nodeList) {
            WebElement button = node.findElement(By.className("btn_inventory"));
            String buttonName = button.getText();
            if (buttonName.equals("Remove")) {
                button.click();
            }
        }
    }
}
