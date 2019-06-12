package nativeapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import logger.Log;
import org.openqa.selenium.By;
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
        Thread.sleep(1000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        Log.info(String.format("Search for %s", text));
    }

    private WebElement getElementByText(String text) throws Exception {
        this.switchToWebContext();
        return driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }

    public void clickText(String text) throws Exception {
        getElementByText(text).click();
    }

    public void verifyTextInResults(String text) throws Exception {
        Assert.assertTrue(getElementByText(text).isDisplayed(),
                String.format("Element with text '%s' is not displayed.", text));
    }
}
