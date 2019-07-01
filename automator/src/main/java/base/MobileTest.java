package base;

import appium.Client;
import appium.Server;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import settings.Settings;

import java.io.IOException;

/**
 * Base test class for all mobile tests.
 */
public class MobileTest {

    protected static AppiumDriver<?> driver;

    private static Settings settings;
    private static Client client;
    private static Server server;

    @BeforeTest
    public static void beforeAll() throws IOException {
        settings = Settings.getInstance();
        client = new Client(settings);
        server = new Server(settings);
        server.start();
        client.start(server.getUrl());
        driver = client.getDriver();
    }

    @BeforeMethod
    public void beforeEach() {
        if (settings.shouldRestartBetweenTests()) {
            driver.resetApp();
        }
    }

    @AfterTest
    public static void afterAll() {
        client.stop();
        server.stop();
    }
}
