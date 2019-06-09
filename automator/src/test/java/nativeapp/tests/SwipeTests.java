package nativeapp.tests;

import base.MobileTest;
import enums.SwipeDirection;
import nativeapp.pages.HomePage;
import nativeapp.pages.Pages;
import nativeapp.pages.SwipePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for swipe demo of wdio sample app.
 */
public class SwipeTests extends MobileTest {

    private SwipePage swipePage;

    @BeforeMethod
    public void beforeLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.SWIPE);
        swipePage = new SwipePage(driver);
    }

    @Test
    public void swipeLeftAndRight() {
        swipePage.swipe(SwipeDirection.LEFT);
        swipePage.swipe(SwipeDirection.LEFT);
        swipePage.swipe(SwipeDirection.LEFT);
        swipePage.swipe(SwipeDirection.LEFT);
    }
}
