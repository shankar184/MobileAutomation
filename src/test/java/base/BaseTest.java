package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.AppiumServerManager;
import utils.CapabilitiesManager;
import utils.EmulatorStarter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AppiumDriver driver;
    CapabilitiesManager capabilitiesManager;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        AppiumServerManager.startServer();
        EmulatorStarter.startEmulator("emulator-5554"); // Change AVD name accordingly

        capabilitiesManager = new CapabilitiesManager();
        String platform = System.getProperty("platform", "android").toLowerCase(); // default: android
        URL appiumServerURL = new URL("http://127.0.0.1:4723");

        if (platform.equals("android")) {
            UiAutomator2Options options = (UiAutomator2Options) capabilitiesManager.getCapabilities(platform);
            driver = new AndroidDriver(appiumServerURL, options);
        } else if (platform.equals("ios")) {
            XCUITestOptions options = (XCUITestOptions) capabilitiesManager.getCapabilities(platform);
            driver = new IOSDriver(appiumServerURL, options);
        } else {
            throw new RuntimeException("Invalid platform: " + platform);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        AppiumServerManager.stopServer();
    }
}
