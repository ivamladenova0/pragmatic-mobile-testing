package settings;

import logger.Log;
import org.openqa.selenium.Platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Properties;


/**
 * Settings class reads global configurations and use them during test execution.
 */
public class Settings {

    private static Settings instance;
    private static final Object lock = new Object();

    private static Properties prop;

    private static Platform platform;
    private static double platformVersion;
    private static String deviceName;
    private static String appPath;
    private static String avdName;
    private static String udid;

    /**
     * Create a Singleton instance of settings (we need only one instance).
     *
     * @return Instance of settings.
     * @throws IOException when fails to read config file passed by `appConfig` property.
     */
    public static Settings getInstance() throws IOException {
        if (instance == null) {
            synchronized (lock) {
                instance = new Settings();
                instance.loadData();
            }
        }
        return instance;
    }


    /**
     * Get all configuration data and assign to related fields.
     *
     * @throws IOException when fails to read config file passed by `appConfig` property.
     */
    private void loadData() throws IOException {
        String projectPath = System.getProperty("user.dir");
        String propertyFilePath = projectPath + File.separator + "src" + File.separator + "test" +
                File.separator + "resources" + File.separator + System.getProperty("appConfig") + ".properties";
        prop = new Properties();
        prop.load(new FileInputStream(propertyFilePath));

        //Get properties from configuration.properties
        String platformString = prop.getProperty("platform");
        if (platformString.toLowerCase().contains("android")) {
            platform = Platform.ANDROID;
        } else if (platformString.toLowerCase().contains("ios")) {
            platform = Platform.IOS;
        } else {
            platform = null;
        }
        String platformVersionString = prop.getProperty("platformVersion");
        platformVersion = Double.parseDouble(platformVersionString);
        deviceName = prop.getProperty("deviceName", "Unknown Device");
        appPath = prop.getProperty("appFileName");
        if (appPath != null) {
            if (appPath.contains("sauce")) {
                Log.info("TODO: Upload app in SauceLabs!");
            } else if (appPath.contains("http")) {
                Log.info("Using test app from URL: " + appPath);
            } else {
                appPath = projectPath + File.separator + "testapp" + File.separator + appPath;
            }
        }
        avdName = prop.getProperty("avdName");

    }

    /**
     * Get value of environment variable.
     *
     * @param variable     environment variable name.
     * @param defaultValue default value (if variable is not set).
     * @return Value of environment variable (or default value).
     */
    public static String getEnvironmentVariable(String variable, String defaultValue) {
        String finalValue = defaultValue;
        String env = System.getenv(variable);
        if (env != null) {
            finalValue = env;
        }
        return finalValue;
    }

    /**
     * Get SauceLabs User Name.
     *
     * @return udid.
     */
    public String getSauceUserName() {
        String defaultValue = getEnvironmentVariable("SAUCE_USER_NAME", null);
        return prop.getProperty("sauceUserName", defaultValue);
    }

    /**
     * Get SauceLabs Access Key.
     *
     * @return udid.
     */
    public String getSauceAccessKey() {
        String defaultValue = getEnvironmentVariable("SAUCE_ACCESS_KEY", null);
        return prop.getProperty("sauceAccessKey", defaultValue);
    }

    /**
     * Get default timeout for waiting elements.
     *
     * @return timeout in seconds.
     */
    public int getDefaultTimeout() {
        return Integer.valueOf(prop.getProperty("timeout", "30"));
    }

    public String getAppiumVersion() {
        return prop.getProperty("appiumVersion");
    }

    /**
     * Get platform type.
     *
     * @return org.openqa.selenium.Platform value.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Get platform version.
     *
     * @return double value.
     */
    public double getPlatformVersion() {
        return platformVersion;
    }

    /**
     * Get device name.
     *
     * @return String.
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Get path to test app.
     *
     * @return full path to app under test.
     */
    public String getAppPath() {
        return appPath;
    }

    /**
     * Get path to test app.
     *
     * @return full path to app under test.
     */
    public String getAppPackage() {
        return prop.getProperty("appPackage");
    }


    /**
     * Get path to test app.
     *
     * @return full path to app under test.
     */
    public String getAppActivity() {
        return prop.getProperty("appActivity");
    }


    /**
     * Get name of Android Virtual Device (emulator).
     *
     * @return avd name.
     */
    public String getAvdName() {
        return avdName;
    }

    /**
     * Get version of ChromeDriver.
     *
     * @return version as string (null if not specified).
     */
    public String getChromeDriverVersion() {
        return prop.getProperty("chromeDriverVersion");
    }

    /**
     * Get browser type.
     *
     * @return version as string (null if not specified).
     */
    public String getBrowserType() {
        return prop.getProperty("browser");
    }

    /**
     * Get unique device identifier of real device.
     *
     * @return udid.
     */
    public String getUdid() {
        return udid;
    }

    /**
     * Determine if app should be restarted between tests.
     *
     * @return boolean value.
     */
    public boolean shouldRestartBetweenTests() {
        return Boolean.parseBoolean(prop.getProperty("restart", "true"));
    }

    /**
     * Determine if Java debugger is attached.
     *
     * @return boolean value.
     */
    public boolean isDebug() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
    }
}
