package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Class para a User Story 16 (Amigos).
 */
public class UserStory16Page {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private By friendsMenuLocator = By.xpath("//span[contains(.,'Friends')]");
    private By userProfileLocator = By.xpath("//div[2]/div"); // Oponente anterior
    private By addFriendBtnLocator = By.xpath("//button[contains(.,'Add as friend')]");

    public UserStory16Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openHomePage(String baseUrl) {
        driver.get(baseUrl + "/en/");
    }

    public void clickFriendsMenu() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(friendsMenuLocator));
        menu.click();
    }

    public void clickUserProfile() {
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(userProfileLocator));
        profile.click();
    }

    public void clickAddFriend() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addFriendBtnLocator));
        btn.click();
    }
}