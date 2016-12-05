package HW3.tests;

import HW3.Randomizer;
import HW3.model.PokerPlayer;
import HW3.pages.EditPlayerPage;
import HW3.pages.LoginPage;
import HW3.pages.PlayersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lilu on 03.12.2016.
 */
public class SearchTest {

    WebDriver driver; // Declare var
    LoginPage loginPage;
    PlayersPage playersPage;
    EditPlayerPage editPlayerPage;
    PokerPlayer player;
    private static Random rnd = new Random();

    /**
     * Preconditions:
     * 1. Open FireFox browser
     * 2. Open application Login Page URL = "http://80.92.229.236:81/auth/login"
     * 3. Set "admin" to Username
     * 4. Set "123" to Password
     * 5. Click on "Log In" button
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String username = "ld" + Randomizer.generateRandomNumbers(3);
        String email = username + "@test.com";
        player = new PokerPlayer(username, email, "123456", "John",
                "Dou", "Kiev", "Shevchenka street", Randomizer.generateRandomNumbers(10));
        playersPage = new PlayersPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.open(driver); //open poker URL
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickLogin();
        editPlayerPage = new EditPlayerPage(driver);
        editPlayerPage.insertPlayer(player);
    }

    /**
     * Steps to reproduce:
     * 1. Search player by username
     * 2. Verify that message should be displayed
     */
    @Test
    public void searchPlayerByUsernameTest() {
        playersPage.searchPlayerByUsername(player);
        List<WebElement> players = playersPage.selectPlayersFromSearchResult(player);
        Assert.assertFalse(players.isEmpty(), "Player is NOT found by username");
        Assert.assertEquals(1, players.size(), "More than one player is found");
    }

    /**
     * Steps to reproduce:
     * 1. Search player by email
     * 2. Verify that message should be displayed
     */
    @Test
    public void searchPlayerByEmail() {
        playersPage.searchPlayerByEmail(player);
        List<WebElement> players = playersPage.selectPlayersFromSearchResult(player);
        Assert.assertFalse(players.isEmpty(), "Player is NOT found by email");
        Assert.assertEquals(1, players.size(), "More than one player is found");
    }

    /**
     * Steps to reproduce:
     * 1. Search player by first name
     * 2. Verify that message should be displayed
     */
    @Test
    public void searchPlayerByFirstName() {
        playersPage.searchPlayerByFirstName(player);
        List<WebElement> players = playersPage.selectPlayersFromSearchResult(player);
        Assert.assertFalse(players.isEmpty(), "Player is NOT found by first name");
        Assert.assertEquals(1, players.size(), "More than one player is found");
    }

    /**
     * Steps to reproduce:
     * 1. Search player by last name
     * 2. Verify that message should be displayed
     */
    @Test
    public void searchPlayerByLastName() {
        playersPage.searchPlayerByLastName(player);
        List<WebElement> players = playersPage.selectPlayersFromSearchResult(player);
        Assert.assertFalse(players.isEmpty(), "Player is NOT found by last name");
        Assert.assertEquals(1, players.size(), "More than one player is found");
    }

    /**
     * Steps to reproduce:
     * 1. Search player by city
     * 2. Verify that message should be displayed
     */
    @Test
    public void searchPlayerByCity() {
        playersPage.searchPlayerByCity(player);
        List<WebElement> players = playersPage.selectPlayersFromSearchResult(player);
        Assert.assertFalse(players.isEmpty(), "Player is NOT found by city");
        Assert.assertEquals(1, players.size(), "More than one player is found");
    }

    /**
     * Postconditions:
     * 1. Search player by username
     * 2. Click on "Delete"
     * 3. Close FireFox browser
     */
    @AfterTest
    public void afterTest() {
        playersPage.searchPlayerByUsername(player);
        playersPage.clickDelete(player);
        driver.quit();
    }
}
