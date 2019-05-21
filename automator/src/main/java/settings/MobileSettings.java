package settings;

import enums.DeviceType;
import enums.PlatformType;

import java.io.File;

/*
Settings for mobile tests.
 */
@SuppressWarnings("unused")
public class MobileSettings {
    public PlatformType PLATFORM = MobileSettings.getPlatformType();
    public DeviceType DEVICE_TYPE = MobileSettings.getDeviceType();
    public double PLATFORM_VERSION = MobileSettings.getPlatformVersion();
    public final String BASE_LOG_DIR = MobileSettings.getBaseOutDir() + File.separator + "logs";
    public String BASE_SCREENSHOT_DIR = MobileSettings.getBaseOutDir() + File.separator + "screenshots";

    private static String getBaseOutDir() {
        String userDir = System.getProperty("user.dir");
        return userDir + File.separator + "build" + File.separator + "reports" + File.separator + "tests";
    }

    private static double getPlatformVersion() {
        return 6.0;
    }

    private static PlatformType getPlatformType() {
        return PlatformType.ANDROID;
    }

    private static DeviceType getDeviceType() {
        return DeviceType.EMULATOR;
    }
}
