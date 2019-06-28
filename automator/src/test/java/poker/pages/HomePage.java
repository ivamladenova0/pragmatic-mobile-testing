package poker.pages;

import base.BasePage;
import enums.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.testng.Assert;

@SuppressWarnings("unused")
public class HomePage extends BasePage {

    @AndroidFindBy(id = "skipBtnTextView")
    private MobileElement skipButton;

    @AndroidFindBy(id = "buttonMenu")
    private MobileElement drawerButton;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void handleIntro(String language) {
        Assert.assertTrue(skipButton.isDisplayed(), "Failed to load home page.");
        for (int i = 0; i < 8; i++) {
            swipe(SwipeDirection.LEFT);
        }
        skipButton.click();
        driver.findElement(By.xpath("//*[@text='" + language + "']")).click();
        driver.findElement(By.xpath("//*[@text='Continue']")).click();
    }

    public void openMenuItem(String item) {
        drawerButton.click();
        driver.findElement(By.xpath("//*[@text='" + item + "']")).click();
    }

    public String getTitle() {
        return driver.findElement(By.id("labelPageName")).getText();
    }
}
