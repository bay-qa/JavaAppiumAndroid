import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by idorovskikh on 1/18/17.
 */
public class BaseTest {
    static AppiumDriver driver;
    private static WebDriverWait driverWait;


    static WebElement waitForClickable(By locator) {
        return driverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    void changeContext(String context) throws InterruptedException {
        Set<String> contextHandles = driver.getContextHandles();

        for (String s: contextHandles){
            System.out.println("Context :" + s);
            if (s.contains(context)){
                System.out.println("Setting context to " + s);
                driver.context(s);
                break;
            }
        }
    }
    private void successfulGoogleLoginWithValidCredential() {
        driver.findElement(By.id("btnGoogleLogin")).click();

        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(By.id("android:id/button1")).click();
        Assert.assertTrue(driver.findElementById("btnHamburger").isDisplayed());

    }

    private void killUiAutomatorServer() throws IOException, InterruptedException{
        Process process = Runtime.getRuntime().exec("adb uninstall io.appium.uiautomator2.server");
        process.waitFor();

        Process process2 = Runtime.getRuntime().exec("adb uninstall io.appium.uiautomator2.server.test");
        process2.waitFor();
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        killUiAutomatorServer();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 6P");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, "true");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "us.moviemates");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".Activities.SplashActivity");
        capabilities.setCapability("autoGrantPermissions", "true"); //grant permission to system dialogues such as location
//      capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/app/app-debug.apk");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver, 10);
        System.out.println(".......Starting Appium driver");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeEachTest()  {
        successfulGoogleLoginWithValidCredential();
    }

    @AfterSuite
    public void tearDown() {
        System.out.println(".......Stopping Appium driver");
        driver.quit();
    }
}
