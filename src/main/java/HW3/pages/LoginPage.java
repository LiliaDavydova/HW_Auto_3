package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Lilu on 01.12.2016.
 */
public class LoginPage {

    public static final String URL = "http://80.92.229.236:81/auth/login";
    public static final String INVALID_CREDENTIALS_ERROR_MSG = "Invalid username or password";
    public static final String EMPTY_CREDENTIALS_ERROR_MSG = "Value is required and can't be empty";
    private WebDriver driver; // страница должна хранить в себе объект драйвера

    public LoginPage(WebDriver driver) {
        this.driver = driver; // связь между внешней ссылкой на драйвер
    }

    public void open(WebDriver driver) {
        driver.get(URL); // Open Poker
    }

    public void setUsername(String username) { // мы можем задать username
        driver.findElement(By.id("username")).clear(); // очистить поле
        driver.findElement(By.id("username")).sendKeys(username);//задать значение
    }

    public void setPassword(String password) { // мы можем задать username
        driver.findElement(By.id("password")).clear(); // очистить поле
        driver.findElement(By.id("password")).sendKeys(password);//задать значение
    }

    public void clickLogin() { // мы можем задать кликнуть на кнопку Login
        driver.findElement(By.id("logIn")).click();
    }

    public String getErrorMessage() {
        WebElement errorMsgElement = driver.findElement(By.xpath("//ul[@class='errors']/li"));
        return errorMsgElement.getText();
    }

/*    public void WaitForCondition() {

        Wait wait = new WebDriverWait(driver. 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        //toBeClipable
    }*/
}
