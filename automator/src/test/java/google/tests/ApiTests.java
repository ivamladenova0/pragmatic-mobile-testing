package google.tests;

import google.api.OpenWeatherAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {
    @Test
    public void smokeTest(){
        int apiTemp1 = OpenWeatherAPI.getTemperature("Sofia,bg");
        int apiTemp2 = OpenWeatherAPI.getTemperature2("Sofia,bg");
        Assert.assertEquals(apiTemp1, apiTemp2);
    }
}
