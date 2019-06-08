package theapp.tests;

import base.MobileTest;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;
import theapp.pages.HomePage;
import theapp.pages.LoginPage;

import java.time.Duration;

class SystemTests extends MobileTest {

    private HomePage home = new HomePage(driver);

    @Test
    void rotateHomeScreen() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        LoginPage login = home.openLoginDemo();
    }

    @Test
    void runInBackground() {
        LoginPage login = home.openLoginDemo();
        driver.runAppInBackground(Duration.ofSeconds(10));
    }
}
