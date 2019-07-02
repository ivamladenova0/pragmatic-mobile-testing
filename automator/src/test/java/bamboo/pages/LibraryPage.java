package bamboo.pages;

import bamboo.enums.NoteType;
import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import logger.Log;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

public class LibraryPage extends BasePage {

    @AndroidFindBy(id = "toolbar_menu_item_cover")
    private MobileElement changeCover;

    @AndroidFindBy(id = "book_properties_done_button")
    private MobileElement saveButton;

    @AndroidFindBy(id = "toolbar_overflow_button")
    private MobileElement dotsMenuButton;

    @AndroidFindBy(id = "fab_image_button")
    private MobileElement addButton;

    public LibraryPage(AppiumDriver driver) {
        super(driver);
    }

    public void changeCover(String image) throws IOException, URISyntaxException {
        dotsMenuButton.click();
        changeCover.click();
        findByImage(image).click();
        Log.info(String.format("Click %s.", image));
    }

    public void verifyBackground(String image) throws IOException, URISyntaxException {
        Assert.assertTrue(findByImage(image).isDisplayed());
        Log.info(String.format("%s background found!", image));
    }

    public void save() {
        saveButton.click();
        Log.info("Save the note.");
    }

    public void addNote(NoteType noteType) {
        addButton.click();
        Log.info("Click add button.");
        By locator = By.id("style_" + noteType.toString().toLowerCase());
        driver.findElement(locator).click();
        Log.info("Selected note type: " + noteType.toString());
    }
}
