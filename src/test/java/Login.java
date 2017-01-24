import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by idorovskikh on 1/17/17.
 */
public class Login extends BaseTest{

    @Test
    public void successfulFacebookLoginWithValidCredential() throws InterruptedException {
        driver.findElement(By.id("btnLogin")).click();

        changeContext("WEBVIEW");

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("");

        driver.hideKeyboard();

        By loginBtn = By.xpath("//button[@name='login']");
        waitForClickable(loginBtn);

        driver.findElement(loginBtn).click();

        By confirmBtn = By.xpath("//button[@name='__CONFIRM__']");
        waitForClickable(confirmBtn);

        driver.findElement(confirmBtn).click();
        changeContext("NATIVE_APP");

        System.out.println(driver.getContext());

        Assert.assertTrue(driver.findElementById("btnHamburger").isDisplayed());
    }
}