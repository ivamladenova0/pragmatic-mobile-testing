package sbtech.tests;

import base.MobileTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import sbtech.pages.HomePage;

import java.net.MalformedURLException;

public class SBTechTests extends MobileTest {

    @Test
    public void homePageSmokeTest() throws MalformedURLException {
        HomePage home = new HomePage(driver);
        home.navigateTo();
        home.goTo("About Us");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.sbtech.com/about-sbtech/");
    }
}
