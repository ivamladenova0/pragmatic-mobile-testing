package google.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import logger.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * WebView page of wdio demo app.
 */
@SuppressWarnings("unused")
public class HomePage extends BasePage {

    @FindBy(id = "search_input_react")
    private WebElement searchBox;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateTo() throws MalformedURLException {
        driver.navigate().to(new URL("https://www.google.com/ncr"));
    }

    public void searchFor(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
        Log.info(String.format("Search for %s", text));
    }

    public void verifyTextInResults(String text) {

    }
}
