package nativeapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;

/**
 * Home screen of wdio native demo app.
 */
public class HomePage extends BasePage {

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateTo(Pages page) {
        driver.findElementByAccessibilityId(String.valueOf(page)).click();
    }
}
