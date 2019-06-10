package nativeapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import logger.Log;

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
        Log.info(String.format("Type %s in input form.", text));
    }

    public String getTypedText() {
        return inputFieldResult.getText();
    }

    public void toggleSwitch() {
        switchButton.click();
    }

    public boolean isSwitchOn() {
        String state = switchButton.getAttribute("value");
        if (state.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    public void selectFromDropDown(String value) {

    }
}
