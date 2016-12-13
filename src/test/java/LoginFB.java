/**
 * Created by idorovskikh on 12/12/16.
 */

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


public class LoginFB {
    AppiumDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 6P");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/idorovskikh/ba_qa_repos/JavaAppiumAndroid/app/app-debug.apk");

        this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterSuite
    public void tearDown(){
        System.out.println(".......Stopping Appium driver");
        driver.quit();
    }

    @Test
    public void successfulLoginWithValidAccount(){
        this.driver.findElement(By.id("btnLogin")).click();

        Set<String> contextHandles = this.driver.getContextHandles();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String s: contextHandles){
            System.out.println("Context :" + s);
            if (s.contains("WEBVIEW")){
                driver.context(s);
            }

        }

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("igordor@yahoo.com");
    }

}
