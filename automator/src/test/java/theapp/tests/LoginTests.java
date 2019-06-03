package theapp.tests;

import base.MobileTest;
import org.junit.jupiter.api.Test;
import theapp.pages.HomePage;

class LoginTests extends MobileTest {

    private HomePage home = new HomePage(driver);

    @Test
    void loginInvalidUser() {
        HomePage login = home.openLoginDemo();
        login.login("Admin", "Admin");
        login.verifyAlert();
    }
}
