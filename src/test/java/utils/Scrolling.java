package utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;

public class Scrolling {
    private static Dimension windowSize;
    private static Duration SCROLL_DUR = Duration.ofMillis(100);
    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private static Dimension getWindowSize(AppiumDriver driver) {
        if (windowSize == null) {
            windowSize = driver.manage().window().getSize();
        }
        return windowSize;
    }

    public static void scroll(AppiumDriver driver,ScrollDirection dir, double scrollRatio) {
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize(driver);
        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
        int top = midPoint.y - (int)(midPoint.y * scrollRatio);
        int bottom = midPoint.y + (int)(midPoint.y * scrollRatio);
        int left = midPoint.x - (int)(midPoint.x*scrollRatio);
        int right = midPoint.x + (int)(midPoint.x*scrollRatio);
        if (dir == ScrollDirection.UP) {
            swipe(driver,new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(driver,new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(driver,new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(driver,new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    protected static void swipe(AppiumDriver driver,Point start, Point end, Duration duration) {

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) driver).perform(ImmutableList.of(swipe));
    }


    public WebElement scrollToElementByText(AppiumDriver driver, String text) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));"));
    }

    public WebElement scrollToTextContains_Android(AppiumDriver driver,String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));

    }
    public static WebElement scrollToElementByDescriptionContains(AppiumDriver driver, String descContains) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().descriptionContains(\""+descContains+"\"));"));
    }
// https://developer.android.com/reference/androidx/test/uiautomator/UiSelector

    public WebElement scrollToId_Android(AppiumDriver driver,String id) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().resourceIdMatches(\".*id.*\"))"));

    }


    public void scroll_Js(WebElement webElement,AppiumDriver driver,double percent)
    {
        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "elementId", ((RemoteWebElement) webElement).getId(),
                        "direction", "down",
                        "percent", percent
                )
        );
    }

    // scrollForward (moves exactly one view)
    public void scrollForward_Android(AppiumDriver driver) {
        driver.findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
    }

    // scrollToBeginning (moves exactly by one view. 10 scrolls max)
    public void scrollToEnd_Android(AppiumDriver driver,int noOfScrolls) {
        driver.findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd("+noOfScrolls+")"));
    }

    // scrollToEnd (moves exactly by one view. 10 scrolls max)
    public void scrollToBeginning_Android(AppiumDriver driver,int noOfScrolls) {
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning("+noOfScrolls+")"));
    }



    // scrollViewElement -- this element must be identified using new UiSelector()
    public void scrollToElementByTextUsingAccessibilityId(AppiumDriver driver, WebElement scrollViewElement, String selector) {
        final HashMap args = new HashMap<String, Object>();
        args.put ("elementId", ((RemoteWebElement) scrollViewElement).getId ());
        args.put ("strategy", "accessibility id");  // search for the required element with accessibility id
        args.put ("selector", selector); // pass the correct accessibility id.
        driver.executeScript("mobile: scroll", args);
    }



}
