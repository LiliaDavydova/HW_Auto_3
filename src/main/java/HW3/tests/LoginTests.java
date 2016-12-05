package HW3.tests;

import HW3.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lilu on 02.12.2016.
 */
public class LoginTests {

    WebDriver driver; // Declare var
    LoginPage loginPage;

    /**
     * Preconditions:
     * 1. Open FireFox browser
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Preconditions:
     * 1.  Open application Login Page URL = "http://80.92.229.236:81/auth/login
     */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open(driver);
    }

    /**
     * Steps to reproduce:
     * 1. Set "admin" to Username
     * 2. Set "123" to Password
     * 3. Click on "Log In" button
     * 4. Verify that title of the page equals to "Players"
     */
    @Test
    public void positiveTest() {
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after login");
        Assert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, "You are still on login page.");
    }

    /**
     * Steps to reproduce:
     * 1. Set "admin" to Username
     * 2. Set "12345" to Password
     * 3. Click on "Log In" button
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that "Validation error message is not valid." message is displayed
     */
    @Test
    public void negativeTestWrongPasssord() {
        loginPage.setUsername("admin");
        loginPage.setPassword("12345");
        loginPage.clickLogin();

        String expectedMsg = LoginPage.INVALID_CREDENTIALS_ERROR_MSG;
        String actualMsg = loginPage.getErrorMessage();
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(actualMsg, expectedMsg, "Validation error message is not valid.");
    }

    /**
     * Steps to reproduce:
     * 1. Set "administrator" to Username
     * 2. Set "123" to Password
     * 3. Click on "Log In" button
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that "Validation error message is not valid." message is displayed
     */
    @Test
    public void negativeTestWrongLogin() {
        loginPage.setUsername("administrator");
        loginPage.setPassword("123");
        loginPage.clickLogin();

        String expectedMsg = LoginPage.INVALID_CREDENTIALS_ERROR_MSG;
        String actualMsg = loginPage.getErrorMessage();
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(actualMsg, expectedMsg, "Validation error message is not valid.");
    }

    /**
     * Steps to reproduce:
     * 1. Username is blank
     * 2. Password is blank
     * 3. Click on "Log In" button
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that "Validation error message is not valid." message is displayed
     */
    @Test
    public void negativeTestEmptyFields() {
        loginPage.setUsername("");
        loginPage.setPassword("");
        loginPage.clickLogin();

        String expectedMsg = LoginPage.EMPTY_CREDENTIALS_ERROR_MSG;
        String actualMsg = loginPage.getErrorMessage();
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(actualMsg, expectedMsg, "Validation error message is not valid.");
    }

    /**
     * Postconditions:
     * 1. Close FireFox browser
     */
    @AfterTest
    public void afterTest() {
        driver.quit();
    }


}
