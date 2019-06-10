package nativeapp.tests;

import base.MobileTest;
import enums.SwipeDirection;
import logger.Log;
import nativeapp.pages.HomePage;
import nativeapp.pages.Pages;
import nativeapp.pages.SwipePage;
import nativeapp.pages.WebViewPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Tests for swipe demo of wdio sample app.
 */
public class WebViewTests extends MobileTest {

    private WebViewPage webViewPage;

    @BeforeMethod
    public void beforeLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.WEB_VIEW);
        webViewPage = new WebViewPage(driver);
    }

    @Test
    public void smokeTest() {
        // Get into the webview and assert that we're not yet at the correct page
        String webContext = webViewPage.getWebContext(driver);
        driver.context(webContext);
        Log.info(driver.getPageSource());
    }
}
