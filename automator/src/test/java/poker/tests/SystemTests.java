package poker.tests;

import base.MobileTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import poker.pages.HomePage;


public class SystemTests extends MobileTest {

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
            ((AndroidDriver)driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
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
    public void startAppWithoutNetwork() {
        ((AndroidDriver)driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
        driver.resetApp();
        homePage.openMenuItem("MY ACCOUNT");
        Assert.assertEquals(homePage.getTitle(), "MY ACCOUNT");
    }
}
