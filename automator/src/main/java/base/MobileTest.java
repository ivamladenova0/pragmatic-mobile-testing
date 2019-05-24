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
import settings.Settings;

import java.io.IOException;

public class MobileTest {
    protected static AppiumDriver driver;
    private static AppiumDriverLocalService service;
    private static Settings settings;

    @BeforeAll
    public static void beforeAll() throws IOException {
        // Get settings
        settings = Settings.getInstance();

        // Start Appium Server
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();

        // Start Appium Client
        driver = new AppiumDriver(service.getUrl(), getCapabilities());
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

    private static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, settings.getAppPath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 120);

        // Set avd name
        String avd = settings.getAvdName();
        if (avd != null) {
            capabilities.setCapability(AndroidMobileCapabilityType.AVD, avd);
        }

        // Set device id
        String udid = settings.getUdid();
        if (udid != null) {
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
        }

        return capabilities;
    }
}
