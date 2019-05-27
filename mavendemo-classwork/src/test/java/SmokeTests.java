import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmokeTests {

    private static AppiumDriver driver;

    @BeforeAll
    static void beforeApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus5Api23");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        caps.setCapability(MobileCapabilityType.APP, "/Users/dtopuzov/Git/pragmatic-mobile-testing/testapps/TheApp-v1.9.0.apk");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 5);
        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        driver = new AppiumDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String sessionId = driver.getSessionId().toString();
        System.out.println(sessionId);
    }

    @Test
    void loginInvalidUser() {
        driver.findElement(By.id("Login Screen")).click();
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).sendKeys("Admin");
        driver.findElement(By.id("loginBtn")).click();
        String message = driver.findElement(By.id("message")).getText();
        driver.findElement(By.id("button1")).click();
        assertEquals("Invalid login credentials, please try again", message);
    }

    @Test
    void rotateLoginScreen() {
        driver.getPageSource();
    }
}
