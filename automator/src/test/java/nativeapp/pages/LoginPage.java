package nativeapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * Login and SignUp page of wdio demo app.
 */
@SuppressWarnings("unused")
public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "button-login-container")
    @iOSXCUITFindBy(accessibility = "button-login-container")
    private MobileElement loginTab;

    @AndroidFindBy(accessibility = "button-sign-up-container")
    @iOSXCUITFindBy(accessibility = "button-sign-up-container")
    private MobileElement signUpTab;

    @AndroidFindBy(accessibility = "input-email")
    @iOSXCUITFindBy(accessibility = "input-email")
    private MobileElement emailBox;

    @AndroidFindBy(accessibility = "input-password")
    @iOSXCUITFindBy(accessibility = "input-password")
    private MobileElement passBox;

    @AndroidFindBy(accessibility = "input-repeat-password")
    @iOSXCUITFindBy(accessibility = "input-repeat-password")
    private MobileElement repeatPassBox;

    @AndroidFindBy(accessibility = "button-LOGIN")
    @iOSXCUITFindBy(accessibility = "button-LOGIN")
    private MobileElement loginButton;

    @AndroidFindBy(accessibility = "button-SIGN UP")
    @iOSXCUITFindBy(accessibility = "button-SIGN UP")
    private MobileElement signUpButton;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String email, String pass) {
        loginTab.click();
        emailBox.clear();
        emailBox.sendKeys(email);
        passBox.clear();
        passBox.sendKeys(pass);
        driver.hideKeyboard();
        loginButton.click();
        Reporter.log(String.format("Login with %s:%s", email, pass));
    }

    public void signUp(String email, String pass, String repeatedPass) {
        signUpTab.click();
        emailBox.clear();
        emailBox.sendKeys(email);
        passBox.clear();
        passBox.sendKeys(pass);
        repeatPassBox.clear();
        repeatPassBox.sendKeys(repeatedPass);
        driver.hideKeyboard();
        signUpButton.click();
        Reporter.log(String.format("SignUp with %s:%s:%s", email, pass, repeatedPass));
    }

    public void signUp(String email, String pass) {
        this.signUp(email, pass, pass);
    }

    public void verifyErrorMessageIsDisplayed(String text) {
        By locator = By.xpath("//android.widget.TextView[@text='" + text + "']");
        MobileElement error = (MobileElement) driver.findElement(locator);
        Assert.assertTrue(error.isDisplayed(), "Error for short password is not displayed!");
    }

    public void verifyDialog(String title, String text) {
        String actualTitle = driver.findElementById("alertTitle").getText();
        Assert.assertEquals(actualTitle, title);

        String actualText = driver.findElementById("message").getText();
        Assert.assertEquals(actualText, text);

        driver.findElementById("button1").click();
        Reporter.log("Close 'Success logged in dialog' with OK.");
    }
}
