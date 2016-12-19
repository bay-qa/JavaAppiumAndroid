import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by idorovskikh on 12/15/16.
 */

@Listeners({ ScreenshotUtility.class })

public class BaseTest {

    static AppiumDriver driver;
    static WebDriverWait driverWait;


    public static WebElement waitForClickable(By locator) {
        return driverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElement(By locator) {

        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void changeContext(String context){
        Set<String> contextHandles = driver.getContextHandles();

        for (String s: contextHandles){
            System.out.println("Context :" + s);
            if (s.contains(context)){
                System.out.println("Setting context to " + s);
                driver.context(s);
            }
        }
    }

    @BeforeTest
    public void successfulFacebookLoginWithValidCredential() {
        driver.findElement(By.id("btnLogin")).click();

        changeContext("WEBVIEW");

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("igordor@yahoo.com");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("Expedia#1");

        driver.hideKeyboard();

        By loginBtn = By.xpath("//button[@name='login']");
        waitForClickable(loginBtn);

        driver.findElement(loginBtn).click();

        By confirmBtn = By.xpath("//button[@name='__CONFIRM__']");
        waitForClickable(confirmBtn);

        driver.findElement(confirmBtn).click();

        System.out.println(driver.getContext());
        changeContext("NATIVE_APP");

        System.out.println(driver.getContext());

        waitForElement(By.id("com.android.packageinstaller:id/dialog_container"));
        driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();

        Assert.assertTrue(driver.findElementById("btnHamburger").isDisplayed());
    }

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 6P");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/app/app-debug.apk");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver, 10);
        System.out.println(".......Starting Appium driver");

    }

    @AfterSuite
    public void tearDown(){
        System.out.println(".......Stopping Appium driver");
        driver.quit();
    }


}

