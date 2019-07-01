package bamboo.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

public class LibraryPage extends BasePage {

    @AndroidFindBy(id = "toolbar_menu_item_cover")
    private MobileElement changeCover;

    public LibraryPage(AppiumDriver driver) {
        super(driver);
    }

    public void changeCover(String image) throws IOException, URISyntaxException {
        changeCover.click();
        findByImage(image).click();
    }

    public void verifyBackground(String image) throws IOException, URISyntaxException {
        Assert.assertTrue(findByImage(image).isDisplayed());
    }
}
