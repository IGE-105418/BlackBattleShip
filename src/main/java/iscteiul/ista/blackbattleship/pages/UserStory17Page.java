package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserStory17Page {
    private WebDriver driver;
    private WebDriverWait wait;

    private By historyMenuLocator = By.xpath("//span[contains(.,'History')]");
    private By matchDetailsLocator = By.xpath("//div[2]/div");

    public UserStory17Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openHomePage(String baseUrl) {
        driver.get(baseUrl + "/en/");
    }

    public void clickHistoryMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(historyMenuLocator)).click();
    }

    public void clickMatchDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(matchDetailsLocator)).click();
    }
}
