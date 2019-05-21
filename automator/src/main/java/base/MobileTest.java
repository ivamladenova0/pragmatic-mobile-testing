package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class MobileTest {
    protected static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @BeforeAll
    public static void beforeAll() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();

        File appDir = new File("testapp");
        File app = new File(appDir, "selendroid-test-app-0.11.0.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator"); // Can be random
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath()); // Path to app under test
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 240); // Timeout to appium commands
        capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 120); // Fail if android device not found in 2 minutes
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE, "io.selendroid.testapp"); // Used to ensure app is running (not crashed at startup)
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "io.selendroid.testapp.HomeScreenActivity"); // Used to ensure app is running (not crashed at startup)
        // Replace Emulator-Api19-Default with name of emulator you want to test
        capabilities.setCapability(AndroidMobileCapabilityType.AVD, "Nexus5Api23");
        // If  you nwat to test on real android device remove the line above and use
        // capabilities.setCapability(MobileCapabilityType.UDID, "<id of your device>"); // Use adb devices to take it.
        driver = new AppiumDriver(service.getUrl(), capabilities);
    }

    @AfterAll
    public static void afterAll() {
        if (driver != null) {
            driver.quit();
        }

        if (service != null) {
            service.stop();
        }
    }
}
