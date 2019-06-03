package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Settings class reads global configurations and use them during test execution.
 */
public class Settings {

    private static Settings instance;
    private static final Object lock = new Object();
    private static String projectPath = System.getProperty("user.dir");
    private static String propertyFilePath = projectPath + File.separator + "src" + File.separator + "test" +
            File.separator + "resources" + File.separator + System.getProperty("appConfig");

    private static String appPath;
    private static String avdName;
    private static String udid;

    private static boolean shouldRestart;


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
        // Declare a properties object
        Properties prop = new Properties();

        //Read configuration.properties file
        prop.load(new FileInputStream(propertyFilePath));

        //Get properties from configuration.properties
        appPath = prop.getProperty("appFileName");

        if (appPath != null) {
            appPath = projectPath + File.separator + "testapp" + File.separator + appPath;
        }

        avdName = prop.getProperty("avdName");

        shouldRestart = Boolean.parseBoolean(prop.getProperty("restart", "true"));
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
     * Get name of Android Virtual Device (emulator).
     *
     * @return avd name.
     */
    public String getAvdName() {
        return avdName;
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
        return shouldRestart;
    }
}
