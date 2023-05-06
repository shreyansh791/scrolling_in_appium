package w3c;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class FingerGestureUtils<D extends AppiumDriver> {
    private final D driver;

    private static final String FINGER_1 = "Finger 1";
    private static final String FINGER_2 = "Finger 2";

    public FingerGestureUtils(D driver) {
        this.driver = driver;
    }

    public void tap(final WebElement element) {
        final var start = getElementCenter(element);
        final var sequence = singleFingerSwipe(FINGER_1, 0, start, null);

        this.driver.perform(singletonList(sequence));
    }

    private Point getElementCenter(final WebElement element) {
        final var location = element.getLocation();

        final var size = element.getSize();
        final var x = (size.getWidth() / 2) + location.getX();

        final var y = (size.getHeight() / 2) + location.getY();
        return new Point(x, y);
//        return getCorrectedCoordinates(element, new Point(x, y));
    }

    private Dimension getScreenSize() {
        return this.driver.manage()
                .window()
                .getSize();
    }


    private Point getCorrectedCoordinates(final WebElement element, final Point point) {
        final var screenSize = getScreenSize();
        var x = point.getX();
        var y = point.getY();
        var w = screenSize.getWidth();
        var h = screenSize.getHeight();

        if (element != null) {
            final var size = element.getSize();
            final var location = element.getLocation();
            w = size.getWidth() + location.getX();
            h = size.getHeight() + location.getY();
            System.out.println("inside getCorrectedCoordinates w is 72 " + w);
            System.out.println("inside getCorrectedCoordinates h is 73 " + h);
        }

        if (x >= w) {
            x = w - 5;
        }
        if (y >= h) {
            y = h - 5;
        }
        if (x < 0) {
            x = 5;
        }
        if (y < 0) {
            y = 5;
        }

        return new Point(x, y);
    }

    private Sequence singleFingerSwipe(final String fingerName, final int index, final Point start, final Point end) {
        final var finger = new PointerInput(TOUCH, fingerName);
        final var sequence = new Sequence(finger, index);

        sequence.addAction(finger.createPointerMove(ZERO, viewport(), start.getX(), start.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        if (end != null) {
            sequence.addAction(new Pause(finger, ofMillis(500)));
            sequence.addAction(finger.createPointerMove(ofMillis(500), viewport(), end.getX(), end.getY()));
        }

        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        return sequence;
    }


}
