package iscteiul.ista.blackbattleship;

import iscteiul.ista.blackbattleship.pages.UserStory17Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserStory17Test {
    private WebDriver driver;
    private UserStory17Page historyPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        historyPage = new UserStory17Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testViewHistory() throws InterruptedException {
        historyPage.openHomePage("https://papergames.io");
        Thread.sleep(1000);

        historyPage.clickHistoryMenu();
        Thread.sleep(1000);

        historyPage.clickMatchDetails();
        Thread.sleep(2000);
    }
}
