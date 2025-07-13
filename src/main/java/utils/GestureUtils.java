package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.text.TextAction;
import java.time.Duration;
import java.util.Arrays;

public class GestureUtils {
    private AppiumDriver driver;
    private final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    public GestureUtils(AppiumDriver driver){
        this.driver = driver;
    }

    // Tap on element
    public void tap(WebElement element) {
        Point point = element.getLocation();
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(tap));
    }

    // Long press
    public void longPress(WebElement element, Duration duration) {
        Point point = element.getLocation();
        Sequence longPress = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, duration))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(longPress));
    }

    // Swipe by coordinates
    public void swipe(int startX, int startY, int endX, int endY, Duration duration) {
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    // Scroll down by percentage
    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        swipe(startX, startY, startX, endY, Duration.ofMillis(1000));
    }

    // Scroll up by percentage
    public void scrollUp() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);

        swipe(startX, startY, startX, endY, Duration.ofMillis(1000));
    }

    // Drag and drop from one element to another
    public void dragAndDrop(WebElement source, WebElement target) {
        Point src = source.getLocation();
        Point tgt = target.getLocation();

        Sequence dragDrop = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), src.x, src.y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), tgt.x, tgt.y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(dragDrop));
    }


}

