package sbtech.tests;

import base.MobileTest;
import org.testng.annotations.Test;
import sbtech.pages.AboutPage;
import sbtech.pages.HomePage;

import java.net.MalformedURLException;

/**
 * Tests for SBTech website.
 */
public class SBTechTests extends MobileTest {

    @Test
    public void homePageSmokeTest() throws MalformedURLException {
        HomePage home = new HomePage(driver);
        home.navigateTo();
        home.acceptPrivacyPolicy();
        home.goTo("About Us");
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.scrollTo("Meet Our People");
        aboutPage.isTextVisible("Richard Carter");
    }
}
