package theapp.tests;

import base.MobileTest;
import org.testng.annotations.Test;
import theapp.pages.HomePage;
import theapp.pages.LoginPage;

class LoginTests extends MobileTest {

    private HomePage home = new HomePage(driver);

    @Test
    void loginInvalidUser() {
        LoginPage login = home.openLoginDemo();
        login.login("Admin", "Admin");
        login.verifyAlert();
    }
}
