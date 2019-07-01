package nativeapp.tests;

import base.MobileTest;
import nativeapp.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * FindByImage demos with native app.
 */
public class FindByImageTests extends MobileTest {

    private HomePage home;

    @BeforeMethod
    public void beforeLoginTest() {
        home = new HomePage(driver);
    }

    @Test
    public void findByImageTest() throws IOException, URISyntaxException {
        home.findByImage("nexus5api23_login_button.png").click();
        home.findByImage("nexus5api23_signup_button.png").click();
        home.findByImage("nexus5api23_signup_button2.png").click();
        home.findByImage("nexus5api23_try_again.png").click();
    }
}
