package google.api;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

/**
 * Wrapper around Open Weather API.
 */
public class OpenWeatherAPI {

    public static int getTemperature(String location) {

        // If this key do not work, please register at https://openweathermap.org/ and get new.
        String apiKey = "f6f077b4f7212d2ca64db91dc84a207c";

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
                queryParam("appid", apiKey).
                when().
                get().
                then().
                statusCode(200).
                extract().
                path("main.temp"); // Extract temperature from response body

        return Math.round(temp);
    }
}
