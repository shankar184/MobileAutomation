package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.AppiumServerManager;
import utils.CapabilitiesManager;
import utils.EmulatorStarter;
import utils.ScreenshotUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({AllureTestNg.class})
public class BaseTest {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    CapabilitiesManager capabilitiesManager;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        AppiumServerManager.startServer();
        EmulatorStarter.startEmulator("emulator-5554");

        capabilitiesManager = new CapabilitiesManager();
        String platform = System.getProperty("platform", "android").toLowerCase();
        URL appiumServerURL = new URL("http://127.0.0.1:4723");

        if (platform.equals("android")) {
            UiAutomator2Options options = (UiAutomator2Options) capabilitiesManager.getCapabilities(platform);
            driver.set(new AndroidDriver(appiumServerURL, options)); //
        } else if (platform.equals("ios")) {
            XCUITestOptions options = (XCUITestOptions) capabilitiesManager.getCapabilities(platform);
            driver.set(new IOSDriver(appiumServerURL, options)); //
        } else {
            throw new RuntimeException("Invalid platform: " + platform);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtils.takeScreenshot(getDriver(), result.getName());
        }

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // ✅ Clean up ThreadLocal
        }

        AppiumServerManager.stopServer();
    }
}
