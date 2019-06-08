package nativeapp.tests;

import base.MobileTest;
import logger.Log;
import org.testng.annotations.Test;

public class SmokeTests extends MobileTest {

    @Test
    public void test1() {
        Log.info("Testing logger.");
    }

    @Test
    public void test2() {
        Log.info("Testing logger.");
    }
}
