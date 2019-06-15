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

    @BeforeMethod
    public void beforeLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.WEB_VIEW);
        webViewPage = new WebViewPage(driver);
    }

    @Test
    public void searchForAppium() throws Exception {
        webViewPage.searchFor("Appium");
        webViewPage.verifyTextInResults("Appium setup");
    }

    @Test
    public void navigation() throws Exception {
        webViewPage.clickText("Guide");
        webViewPage.verifyTextInResults("Welcome to the WebdriverIO");
        webViewPage.clickText("API");
        webViewPage.verifyTextInResults("API Docs");
    }
}
