/**
 * Created by idorovskikh on 12/12/16.
 */

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Login {
    AppiumDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 6P");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/app/app-debug.apk");

        this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println(".......Starting Appium driver");

    }

    @AfterSuite
    public void tearDown(){
        System.out.println(".......Stopping Appium driver");
        driver.quit();
    }

    @Test
    public void successfulFacebookLoginWithValidCredential() {
        driver.findElement(By.id("btnLogin")).click();

        Set<String> contextHandles = driver.getContextHandles();

        for (String s: contextHandles){
            System.out.println("Context :" + s);
            if (s.contains("WEBVIEW")){
                System.out.println("Setting context to " + s);
                driver.context(s);
            }
        }

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("igordor@yahoo.com");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("Expedia#1");

        driver.hideKeyboard();

        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='login']")));

        driver.findElement(By.xpath("//button[@name='login']")).click();

        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='__CONFIRM__']")));

        driver.findElement(By.xpath("//button[@name='__CONFIRM__']")).click();
        driver.context("NATIVE_APP");


        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button")));
        driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();

        Assert.assertTrue(driver.findElementById("llHamburger").isDisplayed());
    }

}
