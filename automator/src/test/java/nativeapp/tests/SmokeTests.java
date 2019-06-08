package nativeapp.tests;

import base.MobileTest;
import logger.Log;
import org.testng.annotations.Test;

class SmokeTests extends MobileTest {

    @Test
    void test1() {
        Log.info("Testing logger.");
    }

    @Test
    void test2() {
        Log.info("Testing logger.");
    }
}
