package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import settings.Settings;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Client {

    private Settings settings;
    private AppiumDriver<?> driver;

    public Client(Settings settings) {
        this.settings = settings;
    }

    public void start(URL url) {
        if (settings.getPlatform() == Platform.IOS) {
            driver = new IOSDriver(url, getCapabilities());
        } else if (settings.getPlatform() == Platform.ANDROID) {
            driver = new AndroidDriver(url, getCapabilities());
        } else {
            driver = new AppiumDriver(url, getCapabilities());
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public AppiumDriver<?> getDriver() {
        return driver;
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, settings.getPlatform());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                String.valueOf(settings.getPlatformVersion()));
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

        // SauceLabs options
        if (settings.getAppiumVersion() != null) {
            capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, settings.getAppiumVersion());
            capabilities.setCapability("username", settings.getSauceUserName());
            capabilities.setCapability("accessKey", settings.getSauceAccessKey());
        }

        return capabilities;
    }
}
