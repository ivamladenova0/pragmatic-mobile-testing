package nativeapp.tests;

import base.MobileTest;
import nativeapp.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Tests for swipe demo of wdio sample app.
 */
public class FindByImageTests extends MobileTest {

    private HomePage home;

    @BeforeMethod
    public void beforeLoginTest() {
        home = new HomePage(driver);
    }

    @Test
    public void findByImageTest() throws IOException, URISyntaxException {
        WebElement element = home.findByImage("nexus5api23_swipe.png");
        element.click();
        element = home.findByImage("nexus5api23_signup.png");
        element.click();
    }
}
