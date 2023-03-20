package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAppPackageAndActivity {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
        caps.setCapability(MobileCapabilityType.APP, appUrl);
        URL url = new URL("http://0.0.0.0:4723/");

        AppiumDriver driver = new AndroidDriver(url, caps);

        //To get the application package name, use the following command:
        String currentPackage = ((AndroidDriver) driver).getCurrentPackage();
        String currentActivity = ((AndroidDriver) driver).currentActivity();
        System.out.println("currentPackage "+currentPackage);
        System.out.println("currentActivity "+currentActivity);

    }
}
