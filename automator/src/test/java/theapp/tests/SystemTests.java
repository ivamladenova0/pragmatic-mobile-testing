package theapp.tests;

import base.MobileTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ScreenOrientation;
import theapp.pages.HomePage;

import java.time.Duration;

class SystemTests extends MobileTest {

    private HomePage home = new HomePage(driver);

    @Test
    void rotateHomeScreen() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        HomePage login = home.openLoginDemo();
    }

    @Test
    void runInBackground() {
        HomePage login = home.openLoginDemo();
        driver.runAppInBackground(Duration.ofSeconds(10));
    }
}
