package HW3.tests;

import HW3.model.PokerPlayer;
import HW3.pages.LoginPage;
import HW3.pages.PlayersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lilu on 03.12.2016.
 */
public class CRUDUserTests {
    WebDriver driver; // Declare var
    LoginPage loginPage;
    PlayersPage playersPage;
    PokerPlayer player;
    private static Random rnd = new Random();

    @BeforeTest
    public void beforeTest() {
        //open browser
        driver = new FirefoxDriver(); //initialize/create object/open firefox
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String username = "ld" + generateRandomNumbers(3);
        String email = username + "@test.com";
        player = new PokerPlayer(username, email, "123456", "John",
                "Dou", "Kiev", "Shevchenka street", generateRandomNumbers(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        playersPage = new PlayersPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.open(driver); //open poker URL
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickLogin();
    }

    @Test(priority = 1)
    public void createPlayerTest() {
        playersPage.insertPlayer(player);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Players";
        Assert.assertEquals(actualTitle, expectedTitle);
        playersPage.searchPlayer(player);
        playersPage.clickEdit(player);
        PokerPlayer actualPlayer = playersPage.getPlayerFromEditForm();
        Assert.assertEquals(actualPlayer.getEmail(), player.getEmail());
        Assert.assertEquals(actualPlayer.getFirstName(), player.getFirstName());
        Assert.assertEquals(actualPlayer.getLastName(), player.getLastName());
        Assert.assertEquals(actualPlayer.getCity(), player.getCity());
        Assert.assertEquals(actualPlayer.getAddress(), player.getAddress());
        Assert.assertEquals(actualPlayer.getPhone(), player.getPhone());
    }

    @Test(priority = 2)
    public void editPlayerTest() {
        playersPage.searchPlayer(player);
        playersPage.clickEdit(player);
        player.setEmail(player.getUsername() + "@mail.ru");
        player.setFirstName("Alice");
        player.setLastName("West");
        player.setCity("Kharkov");
        player.setAddress("Nauky street");
        player.setPhone("0971234567");
        playersPage.editPlayer(player);
        playersPage.searchPlayer(player);
        playersPage.clickEdit(player);
        PokerPlayer actualPlayer = playersPage.getPlayerFromEditForm();
        Assert.assertEquals(actualPlayer.getEmail(), player.getEmail());
        Assert.assertEquals(actualPlayer.getFirstName(), player.getFirstName());
        Assert.assertEquals(actualPlayer.getLastName(), player.getLastName());
        Assert.assertEquals(actualPlayer.getCity(), player.getCity());
        Assert.assertEquals(actualPlayer.getAddress(), player.getAddress());
        Assert.assertEquals(actualPlayer.getPhone(), player.getPhone());
    }

    @AfterTest
    public void afterTest() {
        //close browser
        driver.quit();
    }

    private static String generateRandomNumbers(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }
}
