package sbtech.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * About page of SBTech website.
 */
@SuppressWarnings("unused")
public class AboutPage extends BasePage {

    @FindBy(xpath = "//a[@class='ham-menu icon no-lead-link']")
    private WebElement hamburgerMenu;

    public AboutPage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateTo() throws MalformedURLException {
        driver.navigate().to(new URL("https://www.sbtech.com/"));
    }

    public void scrollTo(String text) {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Log.info(String.format("Scroll to '%s'", text));
    }
}
