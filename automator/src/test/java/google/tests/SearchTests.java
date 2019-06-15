package google.tests;

import base.MobileTest;
import google.pages.HomePage;
import logger.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static io.restassured.RestAssured.when;

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
        home.searchFor("Appium");
        home.verifyTextInResults("Appium: Mobile App Automation Made Awesome.");
    }

    @Test
    public void searchForTemperature() {
        home.searchFor("sofia bulgaria temperature celsius");
        int apiTemp = this.getTemperature("Sofia,bg");
        Log.info("test");
    }

    /**
     * Get current temperature at given location.
     * Notes:
     * - Using https://openweathermap.org/api
     *
     * @param location Location as string.
     * @return current temperature in Celsius.
     */
    private int getTemperature(String location) {
        /*
        Sample GET request:
        https://api.openweathermap.org/data/2.5/weather?q=Sofia&appid=f6f077b4f7212d2ca64db91dc84a207c

        Sample Result:
      {
        "coord": {
            "lon": 23.32,
            "lat": 42.7
        },
        "weather": [{
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03d"
        }],
        "base": "stations",
        "main": {
            "temp": 296.99,
            "pressure": 1016,
            "humidity": 42,
            "temp_min": 288.15,
            "temp_max": 301.15
        },
        "visibility": 10000,
        "wind": {
            "speed": 2.6,
            "deg": 120
        },
        "clouds": {
            "all": 40
        },
        "dt": 1560608641,
        "sys": {
            "type": 1,
            "id": 6366,
            "message": 0.0088,
            "country": "BG",
            "sunrise": 1560566888,
            "sunset": 1560621971
        },
        "timezone": 10800,
        "id": 727011,
        "name": "Sofia",
        "cod": 200
        }
         */

        String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
        String token = "f6f077b4f7212d2ca64db91dc84a207c";
        String url = String.format("%s?q=%s&units=metric&appid=%s", baseUrl, location, token);

        Float apiTemp =
                when().
                        get(url).
                        then().
                        statusCode(200).
                        extract().
                        path("main.temp");

        return Integer.valueOf(String.valueOf(apiTemp));

    }
}
