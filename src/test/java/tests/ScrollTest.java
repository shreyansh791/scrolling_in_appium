package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import utils.Scrolling;
import w3c.FingerGestureUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ScrollTest {

    @Test
    public void scrollTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel_5");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        caps.setCapability("appWaitActivity", "com.wdiodemoapp.MainActivity");
        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "wdio-demo.apk";
        caps.setCapability(MobileCapabilityType.APP, appUrl);

        URL url = new URL("http://0.0.0.0:4723/");

        AppiumDriver driver = new AndroidDriver(url, caps);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(AppiumBy.accessibilityId("Swipe")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Scrolling scrolling = new Scrolling();
        scrolling.scroll(driver, Scrolling.ScrollDirection.DOWN, 0.80);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
