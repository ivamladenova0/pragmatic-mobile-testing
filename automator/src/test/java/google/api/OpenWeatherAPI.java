package google.api;

import google.api.objects.OpenWeatherResponse;
import io.restassured.RestAssured;
import logger.Log;

import static io.restassured.RestAssured.given;

/**
 * Wrapper around Open Weather API.
 */
public class OpenWeatherAPI {

    // If this key do not work, please register at https://openweathermap.org/ and get new.
    private static final String TOKEN = "f6f077b4f7212d2ca64db91dc84a207c";

    public static int getTemperature(String location) {

        // Set base url for all RestAssured requests in this class
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";

        // Enable logging on request/response failure
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Get temperature in Sofia via rest api

        // Actual request is:
        // http://api.openweathermap.org/data/2.5/weather?q=<location>&appid=<api_key>&units=metric
        // queryParam are used to specify query params like appid and units
        Float temp = given().
                queryParam("q", location).
                queryParam("units", "metric").
                queryParam("appid", TOKEN).
                when().
                get().
                then().
                statusCode(200).
                extract().
                path("main.temp"); // Extract temperature from response body
        Log.info(String.format("API Temperature: %s", temp.intValue()));
        return temp.intValue();
    }

    public static int getTemperature2(String location) {
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        OpenWeatherResponse response = given().
                queryParam("q", location).
                queryParam("units", "metric").
                queryParam("appid", TOKEN).
                when().
                get().
                then().
                statusCode(200).extract().body().as(OpenWeatherResponse.class);

        return response.main.temp.intValue();
    }
}
