package pages;

import basepage.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "Login Screen")
    @iOSXCUITFindBy(id = "Login Screen")
    private MobileElement loginScreenButton;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public LoginDemo openLoginDemo() {
        loginScreenButton.click();
        return new LoginDemo(driver);
    }
}
