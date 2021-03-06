package sbtech.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Home page of SBTech website.
 */
@SuppressWarnings("unused")
public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='ham-menu icon no-lead-link']")
    private WebElement hamburgerMenu;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateTo() throws MalformedURLException {
        driver.navigate().to(new URL("https://www.sbtech.com/"));
    }

    public void acceptPrivacyPolicy(){
        driver.findElement(By.xpath("//a[contains(text(), 'I ACCEPT')]")).click();
        Log.info("Accept Privacy Policy.");
    }
    public void goTo(String menuItem) {
        hamburgerMenu.click();
        Log.info("Open hamburger menu.");
        driver.findElement(By.xpath("//a[contains(text(), '" + menuItem + "')]")).click();
        Log.info(String.format("Navigate to '%s'.", menuItem));
    }
}
