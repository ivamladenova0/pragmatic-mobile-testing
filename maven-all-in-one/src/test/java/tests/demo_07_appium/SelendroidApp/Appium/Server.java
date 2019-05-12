package tests.demo_07_appium.SelendroidApp.Appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Server {

    public static AppiumDriverLocalService service;

    public static void startAppiumServer() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort() // Will search for any free port and use if for appium server
                .withStartUpTimeOut(2, TimeUnit.MINUTES) // Stop if server fails to start in 2 minutes
                .withArgument(GeneralServerFlag.LOG_LEVEL, "warn"); // Show appium logs only for "warn" and above

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("Client server is not started!");
        }
    }

    public static void stopAppiumServer() {
        if (service != null) {
            service.stop();
        }
    }
}
