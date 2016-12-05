package HW3.pages;

import HW3.model.PokerPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

/**
 * Created by Lilu on 03.12.2016.
 */
public class EditPlayerPage {
    public static final String INSERT_URL = "http://80.92.229.236:81/players/insert";
    private WebDriver driver;
    private static final String USERNAME_ID = "ff14642ac1c__us_login";
    private static final String EMAIL_ID = "ff14642ac1c__us_email";
    private static final String PASSWORD_ID = "ff14642ac1c__us_password";
    private static final String CONFIRM_PASSWORD_ID = "ff14642ac1c__confirm_password";
    private static final String FIRST_NAME_ID = "ff14642ac1c__us_fname";
    private static final String LAST_NAME_ID = "ff14642ac1c__us_lname";
    private static final String CITY_ID = "ff14642ac1c__us_city";
    private static final String ADDRESS_ID = "ff14642ac1c__us_address";
    private static final String PHONE_ID = "ff14642ac1c__us_phone";

    public EditPlayerPage(WebDriver driver){
        this.driver = driver;
    }

    public void insertPlayer(PokerPlayer player) {
        WebElement insertButton = driver.findElement(By.linkText("Insert"));
        insertButton.click();

        WebElement usernameInput = driver.findElement(By.id(USERNAME_ID));
        usernameInput.sendKeys(player.getUsername());

        WebElement emailInput = driver.findElement(By.id(EMAIL_ID));
        emailInput.sendKeys(player.getEmail());

        WebElement passwordInput = driver.findElement(By.id(PASSWORD_ID));
        passwordInput.sendKeys(player.getPassword());

        WebElement confirmPasswordInput = driver.findElement(By.id(CONFIRM_PASSWORD_ID));
        confirmPasswordInput.sendKeys(player.getPassword());

        WebElement firstNameInput = driver.findElement(By.id(FIRST_NAME_ID));
        firstNameInput.sendKeys(player.getFirstName());

        WebElement lastNameInput = driver.findElement(By.id(LAST_NAME_ID));
        lastNameInput.sendKeys(player.getLastName());

        WebElement cityInput = driver.findElement(By.id(CITY_ID));
        cityInput.sendKeys(player.getCity());

        WebElement addressInput = driver.findElement(By.id(ADDRESS_ID));
        addressInput.sendKeys(player.getAddress());

        WebElement phoneInput = driver.findElement(By.id(PHONE_ID));
        phoneInput.sendKeys(player.getPhone());

        WebElement saveButton = driver.findElement(By.name("button_save"));
        saveButton.click();
    }

    public PokerPlayer getPlayerFromEditForm(){
        PokerPlayer player = new PokerPlayer();
        player.setEmail(driver.findElement(By.id(EMAIL_ID)).getAttribute("value"));
        player.setFirstName(driver.findElement(By.id(FIRST_NAME_ID)).getAttribute("value"));
        player.setLastName(driver.findElement(By.id(LAST_NAME_ID)).getAttribute("value"));
        player.setCity(driver.findElement(By.id(CITY_ID)).getAttribute("value"));
        player.setAddress(driver.findElement(By.id(ADDRESS_ID)).getAttribute("value"));
        player.setPhone(driver.findElement(By.id(PHONE_ID)).getAttribute("value"));
        return player;
    }

    public void editPlayer(PokerPlayer player) {
        WebElement emailInput = driver.findElement(By.id(EMAIL_ID));
        emailInput.clear();
        emailInput.sendKeys(player.getEmail());

        WebElement firstNameInput = driver.findElement(By.id(FIRST_NAME_ID));
        firstNameInput.clear();
        firstNameInput.sendKeys(player.getFirstName());

        WebElement lastNameInput = driver.findElement(By.id(LAST_NAME_ID));
        lastNameInput.clear();
        lastNameInput.sendKeys(player.getLastName());

        WebElement cityInput = driver.findElement(By.id(CITY_ID));
        cityInput.clear();
        cityInput.sendKeys(player.getCity());

        WebElement addressInput = driver.findElement(By.id(ADDRESS_ID));
        addressInput.clear();
        addressInput.sendKeys(player.getAddress());

        WebElement phoneInput = driver.findElement(By.id(PHONE_ID));
        phoneInput.clear();
        phoneInput.sendKeys(player.getPhone());

        WebElement saveButton = driver.findElement(By.name("button_save"));
        saveButton.click();
    }

    public String getErrorMessage() {
        WebElement errorMsgElement = driver.findElement(By.xpath("//div[@class='form_errors_container']/ul"));
        return errorMsgElement.getText();
    }

}

