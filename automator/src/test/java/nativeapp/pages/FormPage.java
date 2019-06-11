package nativeapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import logger.Log;
import org.openqa.selenium.Platform;
import settings.Settings;

import java.io.IOException;

/**
 * Swipe page of wdio demo app.
 */
@SuppressWarnings("unused")
public class FormPage extends BasePage {

    @AndroidFindBy(accessibility = "text-input")
    @iOSXCUITFindBy(accessibility = "text-input")
    private MobileElement textInputField;

    @AndroidFindBy(accessibility = "input-text-result")
    @iOSXCUITFindBy(accessibility = "input-text-result")
    private MobileElement inputFieldResult;

    @AndroidFindBy(accessibility = "switch")
    @iOSXCUITFindBy(accessibility = "switch")
    private MobileElement switchButton;

    public FormPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillInput(String text) {
        textInputField.clear();
        textInputField.sendKeys(text);
        driver.hideKeyboard();
        Log.info(String.format("Type %s in input form.", text));
    }

    public String getTypedText() {
        return inputFieldResult.getText();
    }

    public void toggleSwitch() {
        switchButton.click();
    }

    public boolean isSwitchOn() throws IOException {
        if (Settings.getInstance().getPlatform() == Platform.IOS) {
            return !switchButton.getAttribute("value").equals("0");
        } else {
            return !switchButton.getAttribute("text").equals("OFF");
        }
    }

    public void selectFromDropDown(String value) {

    }
}
