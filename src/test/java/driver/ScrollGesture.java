package driver;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ScrollGesture {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel_5");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
        caps.setCapability(MobileCapabilityType.APP, appUrl);

        URL url = new URL("http://0.0.0.0:4723/");

        AppiumDriver driver = new AndroidDriver(url, caps);

        driver.findElement(AppiumBy.accessibilityId("Views")).click();


//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector()." +
//                        "textContains(\"WebView\"))")); // this code also works.

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"WebView\")"));

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector()." +
                        "text(\"Animation\"))")).click();


//        boolean canScrollMore = true;
//        while (canScrollMore)
//            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
//                    "left", 100, "top", 100, "width", 600, "height", 600,
//                    "direction", "down",
//                    "percent", 0.75
//            ));
//        System.out.println("can scroll more " + canScrollMore);

//        WebElement element = driver.findElement(AppiumBy.id("android:id/list"));
//        canScrollMore = true;
//
//        Thread.sleep(5000);
//        while (canScrollMore) {
//            canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
//                  "elementId",((RemoteWebElement)element).getId(),
//                    "direction", "up",
//                    "percent", 0.75
//                    )
//            );
//            System.out.println("can scroll more 1 " + canScrollMore);
//        }
//
//
//    }


    }
}