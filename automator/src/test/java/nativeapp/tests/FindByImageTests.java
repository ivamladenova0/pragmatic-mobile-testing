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
        home.findByImage("nexus5api23_webview.png").click();
        WebElement gitIcon = home.findByImage("nexus5api23_github.png");
        Assert.assertTrue(gitIcon.isDisplayed(), "Failed to find GitHub icon on WebView page.");
    }
}
