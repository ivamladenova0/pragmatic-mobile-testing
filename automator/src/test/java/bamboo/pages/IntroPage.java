package bamboo.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import logger.Log;

public class IntroPage extends BasePage {

    @AndroidFindBy(id = "btn_go_next")
    private MobileElement nextButton;

    @AndroidFindBy(id = "btn_not_now")
    private MobileElement goToLibrary;

    public IntroPage(AppiumDriver driver) {
        super(driver);
    }

    public void openLibrary() throws InterruptedException {
        nextButton.click();
        Thread.sleep(1000);
        nextButton.click();
        Thread.sleep(1000);
        nextButton.click();
        Thread.sleep(1000);
        Log.info("Skip intro.");
        goToLibrary.click();
        Thread.sleep(1000);
        Log.info("Open Library.");
        driver.navigate().back();
        Log.info("Hit back button to close modal dialog.");
    }
}
