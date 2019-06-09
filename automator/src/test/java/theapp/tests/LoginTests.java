package theapp.tests;

import base.MobileTest;
import org.testng.annotations.Test;
import theapp.pages.HomePage;
import theapp.pages.LoginPage;

/**
 * Tests for login demo.
 */
public class LoginTests extends MobileTest {

    private HomePage home = new HomePage(driver);

    @Test
    public void loginInvalidUser() {
        LoginPage login = home.openLoginDemo();
        login.login("Admin", "Admin");
        login.verifyAlert();
    }
}
