package basetest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static AppiumDriverLocalService service;

    @BeforeAll
    static void beforeAll() {
        startServer();
        startIOS();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
        service.stop();
    }

    @BeforeEach
    void beforeTest() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @AfterEach
    void afterEach() {
        driver.closeApp();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void startServer() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
    }

    static void startIOS(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.3");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        caps.setCapability(MobileCapabilityType.APP, "/Users/dtopuzov/Git/pragmatic-mobile-testing/testapps/TheApp-v1.9.0.app.zip");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 5);
        URL url = service.getUrl();

        driver = new AppiumDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String sessionId = driver.getSessionId().toString();
        System.out.println(sessionId);
    }

    static void startClient() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus5Api23");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        caps.setCapability(MobileCapabilityType.APP, "/Users/dtopuzov/Git/pragmatic-mobile-testing/testapps/TheApp-v1.9.0.apk");
        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE, "io.cloudgrey.the_app");
        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".MainActivity");
        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, 30);
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 5);
        caps.setCapability(MobileCapabilityType.ORIENTATION, ScreenOrientation.PORTRAIT);
        URL url = service.getUrl();

        driver = new AppiumDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String sessionId = driver.getSessionId().toString();
        System.out.println(sessionId);
    }
}
