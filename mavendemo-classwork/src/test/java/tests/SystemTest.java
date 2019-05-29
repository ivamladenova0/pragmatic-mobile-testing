package tests;

import basetest.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginDemo;

class SystemTest extends BaseTest {

    private HomePage home = new HomePage(driver);

    @Test
    void loginInvalidUser() {
        LoginDemo login = home.openLoginDemo();
        login.login("Admin", "Admin");
        login.verifyAlert();
    }
}
