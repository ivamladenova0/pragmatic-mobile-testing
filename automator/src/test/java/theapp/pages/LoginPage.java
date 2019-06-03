package theapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "username")
    @iOSXCUITFindBy(id = "username")
    private MobileElement user;

    @AndroidFindBy(id = "password")
    @iOSXCUITFindBy(id = "password")
    private MobileElement pass;

    @AndroidFindBy(id = "loginBtn")
    @iOSXCUITFindBy(id = "loginBtn")
    private MobileElement login;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String userName, String password) {
        driver.getKeyboard();
        user.sendKeys(userName);
        pass.sendKeys(password);
        login.click();
    }

    public void verifyAlert() {
        String message = driver.findElement(By.id("message")).getText();
        driver.findElement(By.id("button1")).click();
        assertEquals("Invalid login credentials, please try again", message);
    }
}