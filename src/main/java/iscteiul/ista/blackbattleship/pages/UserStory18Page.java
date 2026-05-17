package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserStory18Page {
    private WebDriver driver;
    private WebDriverWait wait;

    private By shopMenuLocator = By.xpath("//span[contains(.,'Shop')]");
    private By monsterAvatarLocator = By.xpath("//img[@alt='Monsters']");
    private By buyButtonLocator = By.xpath("//app-avatar-product[2]/div/app-shop-purchase-product-button/button");
    private By confirmButtonLocator = By.xpath("//span[contains(.,'Confirm')]");

    public UserStory18Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openHomePage(String baseUrl) {
        driver.get(baseUrl + "/en/");
    }

    public void clickShopMenu() { wait.until(ExpectedConditions.elementToBeClickable(shopMenuLocator)).click(); }
    public void selectMonsterAvatar() { wait.until(ExpectedConditions.elementToBeClickable(monsterAvatarLocator)).click(); }
    public void clickBuy() { wait.until(ExpectedConditions.elementToBeClickable(buyButtonLocator)).click(); }
    public void clickConfirm() { wait.until(ExpectedConditions.elementToBeClickable(confirmButtonLocator)).click(); }
}
