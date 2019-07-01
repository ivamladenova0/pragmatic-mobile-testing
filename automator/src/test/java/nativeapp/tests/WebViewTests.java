package nativeapp.tests;

import base.MobileTest;
import nativeapp.pages.HomePage;
import nativeapp.pages.Pages;
import nativeapp.pages.WebViewPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for WebView demo of wdio sample app.
 */
public class WebViewTests extends MobileTest {

    private WebViewPage webViewPage;

    @BeforeMethod(enabled = false)
    public void beforeLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.WEB_VIEW);
        webViewPage = new WebViewPage(driver);
    }

    @Test(enabled = false)
    public void searchForAppium() throws Exception {
        webViewPage.searchFor("Appium");
        webViewPage.verifyTextInResults("Appium setup");
    }
}
