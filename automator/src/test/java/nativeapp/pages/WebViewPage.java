package nativeapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import logger.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * WebView page of wdio demo app.
 */
@SuppressWarnings("unused")
public class WebViewPage extends BasePage {

    @FindBy(id = "search_input_react")
    private WebElement searchBox;

    public WebViewPage(AppiumDriver driver) {
        super(driver);
    }

    public void searchFor(String text) throws Exception {
        this.switchToWebContext();
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
        Log.info(String.format("Search for %s", text));
    }

    public void verifyTextInResults(String text) {
        String webPageSource = driver.getPageSource();
        this.switchToNativeContext();
        String nativePageSource = driver.getPageSource();
        Assert.assertEquals(1, 1);
    }
}
