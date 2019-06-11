package nativeapp.tests;

import base.MobileTest;
import nativeapp.pages.FormPage;
import nativeapp.pages.HomePage;
import nativeapp.pages.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Tests for swipe demo of wdio sample app.
 */
public class FormTests extends MobileTest {

    private FormPage formPage;

    @BeforeMethod
    public void beforeLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.FORMS);
        formPage = new FormPage(driver);
    }

    @Test
    public void fillTheForm() {
        formPage.fillInput("Pragmatic");
        String typedText = formPage.getTypedText();
        Assert.assertEquals(typedText, "Pragmatic");
    }

    @Test
    public void playWithSwitch() throws IOException {
        boolean initialState = formPage.isSwitchOn();
        formPage.toggleSwitch();
        boolean finalState = formPage.isSwitchOn();
        Assert.assertNotEquals(finalState, initialState);
    }
}
