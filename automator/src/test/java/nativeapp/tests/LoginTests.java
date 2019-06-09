package nativeapp.tests;

        import base.MobileTest;
        import nativeapp.pages.HomePage;
        import nativeapp.pages.LoginPage;
        import nativeapp.pages.Pages;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;

/**
 * Tests for login demo of wdio sample app.
 */
public class LoginTests extends MobileTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void beforeLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.LOGIN);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginWithValidUser() {
        loginPage.login("admin@gmail.com", "Admin12345");
        loginPage.verifyDialog("Success", "You are logged in!");
    }

    @Test
    public void loginWithShortUserPass() {
        loginPage.login("admin@gmail.com", "Admin");
        loginPage.verifyErrorMessageIsDisplayed("Please enter at least 8 characters");
    }

    @Test
    public void signUpWithValidUser() {
        loginPage.signUp("admin@gmail.com", "Admin12345");
        loginPage.verifyDialog("Signed Up!", "You successfully signed up!");
    }

    @Test
    public void signUpWithNotMatchingPass() {
        loginPage.signUp("admin@gmail.com", "Admin123", "Admin124");
        loginPage.verifyDialog("Failure", "Some fields are not valid!");
        loginPage.verifyErrorMessageIsDisplayed("Please enter the same password");
    }
}
