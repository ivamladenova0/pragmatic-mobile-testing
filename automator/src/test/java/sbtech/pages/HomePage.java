package sbtech.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='ham-menu icon no-lead-link']")
    private WebElement hamburgerMenu;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateTo() throws MalformedURLException {
        driver.navigate().to(new URL("https://www.sbtech.com/"));
    }

    public void goTo(String menuItem) {
        hamburgerMenu.click();
        driver.findElement(By.linkText(menuItem)).click();
    }
}
