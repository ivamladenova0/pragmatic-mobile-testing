package appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import settings.Settings;

import java.net.MalformedURLException;
import java.net.URL;

public class Server {
    private Settings settings;
    private AppiumDriverLocalService service;

    public Server(Settings settings) {
        this.settings = settings;
    }

    public void start() {
        if (isLocal()) {
            // Construct AppiumServiceBuilder
            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "warn");

            // Allow RELAXED_SECURITY for local execution
            if (settings.getSauceUserName() == null) {
                serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
            }

            // Start Appium Server
            service = AppiumDriverLocalService.buildService(serviceBuilder);
            service.start();
        }
    }

    public URL getUrl() throws MalformedURLException {
        if (isLocal()) {
            return service.getUrl();
        } else {
            return new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
        }
    }

    public void stop() {
        if (isLocal()) {
            if (service != null) {
                service.stop();
            }
        }
    }

    private boolean isLocal() {
        return settings.getSauceUserName() == null;
    }
}
