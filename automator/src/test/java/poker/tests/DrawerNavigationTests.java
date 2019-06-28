package poker.tests;

import base.MobileTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import poker.pages.HomePage;

public class DrawerNavigationTests extends MobileTest {

    private HomePage homePage;
    private boolean failed = false;

    @BeforeClass
    public void beforeDrawerNavigationTests() {
        homePage = new HomePage(driver);
        homePage.handleIntro("ENGLISH");
    }

    @BeforeMethod
    public void beforeEach(ITestResult result) {
        if (failed) {
            driver.resetApp();
            homePage.handleIntro("ENGLISH");
        }
    }

    @AfterMethod
    public void afterEach(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            failed = true;
        }
    }

    @Test
    public void myAccount() {
        homePage.openMenuItem("MY ACCOUNT");
        Assert.assertEquals(homePage.getTitle(), "MY ACCOUNT");
    }

    @Test
    public void about() {
        homePage.openMenuItem("ABOUT");
        Assert.assertEquals(homePage.getTitle(), "ABOUT");
    }

    @Test
    public void help() {
        homePage.openMenuItem("HELP");
        Assert.assertEquals(homePage.getTitle(), "HELP");
    }
}
