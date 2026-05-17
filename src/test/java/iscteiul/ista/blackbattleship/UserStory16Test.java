package iscteiul.ista.blackbattleship;

import iscteiul.ista.blackbattleship.pages.UserStory16Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class UserStory16Test {
    private WebDriver driver;
    private UserStory16Page friendsPage;
    private final String BASE_URL = "https://papergames.io";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        friendsPage = new UserStory16Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testAddFriend() throws InterruptedException {
        friendsPage.openHomePage(BASE_URL);
        Thread.sleep(1000);

        friendsPage.clickFriendsMenu();
        Thread.sleep(1000);

        // Se a lista de amigos estiver num iframe, descomentar:
        // driver.switchTo().frame(7);

        friendsPage.clickUserProfile();
        Thread.sleep(500);
        friendsPage.clickAddFriend();

        Thread.sleep(2000);
    }
}
