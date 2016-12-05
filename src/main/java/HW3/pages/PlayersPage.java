package HW3.pages;

import HW3.model.PokerPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Lilu on 03.12.2016.
 */
public class PlayersPage {
    private WebDriver driver;
    private static final String USERNAME_ID = "723a925886__login";
    private static final String EMAIL_ID = "723a925886__email";
    private static final String FIRST_NAME_ID = "723a925886__firstname";
    private static final String LAST_NAME_ID = "723a925886__lastname";
    private static final String CITY_ID = "723a925886__city";

    public PlayersPage(WebDriver driver){
        this.driver = driver;
    }

    public void resetSearchForm(){
        WebElement resetButton = driver.findElement(By.name("reset"));
        resetButton.click();
    }

    public void searchPlayerByUsername(PokerPlayer player) {
        resetSearchForm();
        WebElement usernameInput = driver.findElement(By.id(USERNAME_ID));
        usernameInput.sendKeys(player.getUsername());

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.click();
    }

    public void searchPlayerByEmail(PokerPlayer player) {
        resetSearchForm();
        WebElement emailInput = driver.findElement(By.id(EMAIL_ID));
        emailInput.sendKeys(player.getEmail());

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.click();
    }

    public void searchPlayerByFirstName(PokerPlayer player) {
        resetSearchForm();
        WebElement firstNameInput = driver.findElement(By.id(FIRST_NAME_ID));
        firstNameInput.sendKeys(player.getFirstName());

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.click();
    }

    public void searchPlayerByLastName(PokerPlayer player) {
        resetSearchForm();
        WebElement lastNameInput = driver.findElement(By.id(LAST_NAME_ID));
        lastNameInput.sendKeys(player.getLastName());

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.click();
    }

    public void searchPlayerByCity(PokerPlayer player) {
        resetSearchForm();
        WebElement cityInput = driver.findElement(By.id(CITY_ID));
        cityInput.sendKeys(player.getCity());

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.click();
    }

    public List<WebElement> selectPlayersFromSearchResult(PokerPlayer player){
        return driver.findElements(By.xpath(".//a[.='" + player.getUsername() + "']"));
    }

    public void clickEdit(PokerPlayer player) {
        driver.findElement(By.xpath(".//tr[.//a[text()='" + player.getUsername() + "']]//img[@title='Edit']")).click();
    }

    public void clickDelete(PokerPlayer player) {
        driver.findElement(By.xpath(".//tr[.//a[text()='" + player.getUsername() + "']]//img[@title='Delete']")).click();
    }

    public WebElement getSuccessfulDeleteMsgElement() {
        return driver.findElement(By.xpath("//*[contains(text(),'Player has been deleted')]"));
    }
}
