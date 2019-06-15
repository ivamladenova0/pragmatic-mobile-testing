package google.tests;

import base.MobileTest;
import google.pages.HomePage;
import org.testng.annotations.Test;

/**
 * Tests for google search.
 */
public class SearchTests extends MobileTest {

    @Test
    public void searchForPragmatic() throws Exception {
        HomePage home = new HomePage(driver);
        home.navigateTo();
        home.searchFor("Appium");
        home.verifyTextInResults("Appium: Mobile App Automation Made Awesome.");
    }
}
