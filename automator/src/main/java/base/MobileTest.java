package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import settings.Settings;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Base test class for all mobile tests.
 */
public class MobileTest {
    protected static AppiumDriver<?> driver;
    private static AppiumDriverLocalService service;
    private static Settings settings;

    @BeforeTest
    public static void beforeAll() throws IOException {
        // Get settings
        settings = Settings.getInstance();

        // Start Appium Server
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "warn");

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();

        // Start Appium Client and set implicitly wait of 30sec.
        if (settings.getPlatform() == Platform.IOS) {
            driver = new IOSDriver(service.getUrl(), getCapabilities());
        }else{
            driver = new AndroidDriver(service.getUrl(), getCapabilities());
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeEach() {
        if (settings.shouldRestartBetweenTests()) {
            driver.resetApp();
        }
    }

    @AfterTest
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
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, settings.getPlatform());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, settings.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, settings.getDeviceName());

        // Set application under test
        if (settings.getAppPath() != null) {
            capabilities.setCapability(MobileCapabilityType.APP, settings.getAppPath());
        }
        if (settings.getAppPackage() != null) {
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, settings.getAppPackage());
        }
        if (settings.getAppActivity() != null) {
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, settings.getAppActivity());
        }

        // Set command timeout (in debug mode timeout is huge to allow normal debugging)
        if (settings.isDebug()) {
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);
        } else {
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        }

        // Set Android specific settings.
        if (settings.getPlatform() == Platform.ANDROID) {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
            capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 120);
            String avd = settings.getAvdName();
            if (avd != null) {
                capabilities.setCapability(AndroidMobileCapabilityType.AVD, avd);
            }
        }

        // Set iOS specific settings.
        if (settings.getPlatform() == Platform.IOS) {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        }

        // Set device id.
        String udid = settings.getUdid();
        if (udid != null) {
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
        }

        // Set web capabilities
        String browser = settings.getBrowserType();
        if (browser != null) {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
        }

        // Set WebView options
        String chromeDriverVersion = settings.getChromeDriverVersion();
        if (chromeDriverVersion != null) {
            WebDriverManager.chromedriver().version(chromeDriverVersion).setup();
            String path = WebDriverManager.chromedriver().version(chromeDriverVersion).getBinaryPath();
            capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, path);
        }

        return capabilities;
    }
}
