package utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

public class CapabilitiesManager {

    public static Object getCapabilities(String platform) {
        String platformName = System.getProperty("platformName", "Android");

        if (platformName.equalsIgnoreCase("Android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("emulator-5554");
            options.setAutomationName("UiAutomator2");
            options.setApp(System.getProperty("user.dir") + "/apps/app-debug.apk");
            options.setFullReset(true);
            options.setNoReset(false);
            options.setAutoGrantPermissions(true); // autoAcceptAlerts alternative
            return options;

        } else if (platformName.equalsIgnoreCase("iOS")) {
            XCUITestOptions options = new XCUITestOptions();
            options.setPlatformName("iOS");
            options.setDeviceName("iPhone 14");
            options.setPlatformVersion("17.0");
            options.setAutomationName("XCUITest");
            options.setApp(System.getProperty("user.dir") + "/apps/app-ios.app");
            options.setFullReset(true);
            options.setNoReset(false);
            options.setAutoAcceptAlerts(true);
            return options;

        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }
    }
}
