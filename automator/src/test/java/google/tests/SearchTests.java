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
        home.searchFor("Who developed Appium?");
        home.verifyTextInResults("Dan Cuellar");
    }

    @Test
    public void searchForTemperature() {
        home.searchFor("sofia bulgaria temperature celsius");
        int apiTemp = OpenWeatherAPI.getTemperature2("Sofia,bg");
        int webTemp = home.getTemperatureValue();
        Assert.assertTrue(Math.abs(apiTemp - webTemp) < 10, "Temperature is wrong!");
    }
}
