package google.tests;

import base.MobileTest;
import google.api.OpenWeatherAPI;
import google.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Tests for google search.
 */
public class SearchTests extends MobileTest {

    private HomePage home;

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        home = new HomePage(driver);
        home.navigateTo();
    }

    @Test
    public void searchForAppium() {
        home.searchFor("appium io");
        home.verifyTextInResults("Appium: Mobile App Automation Made Awesome.");
    }

    @Test
    public void searchForTemperature() {
        home.searchFor("sofia bulgaria temperature celsius");
        int apiTemp = OpenWeatherAPI.getTemperature("Sofia,bg");
        int webTemp = home.getTemperatureValue();
        Assert.assertTrue(Math.abs(apiTemp - webTemp) < 5, "Temperature is wrong!");
    }
}
