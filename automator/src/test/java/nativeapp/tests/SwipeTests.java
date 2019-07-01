package nativeapp.tests;

import base.MobileTest;
import enums.SwipeDirection;
import logger.Log;
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
    public void beforeSwipeTest() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(Pages.SWIPE);
        swipePage = new SwipePage(driver);
    }

    @Test
    public void swipeLeftAndRight() {
        for (int i = 0; i < 4; i++) {
            swipePage.swipe(SwipeDirection.LEFT);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.info("test");
    }
}
