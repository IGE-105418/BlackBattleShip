package iscteiul.ista.blackbattleship;

import iscteiul.ista.blackbattleship.pages.UserStory18Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserStory18Test {
    private WebDriver driver;
    private UserStory18Page shopPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        shopPage = new UserStory18Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testBuyAvatar() throws InterruptedException {
        shopPage.openHomePage("https://papergames.io");
        Thread.sleep(1000);

        shopPage.clickShopMenu();
        Thread.sleep(1000);

        shopPage.selectMonsterAvatar();
        Thread.sleep(1000);

        shopPage.clickBuy();
        Thread.sleep(1000);

        shopPage.clickConfirm();
        Thread.sleep(2000);
    }
}