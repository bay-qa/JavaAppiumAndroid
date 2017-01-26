package RegressionTests;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
 * Created by idorovskikh on 1/17/17.
 */
public class FacebookLogin extends BaseTest {


    @BeforeMethod(groups = "regression")
    public void successfulFacebookLoginWithValidCredential() throws InterruptedException {
        driver.findElement(By.id("btnLogin")).click();

        changeContext("WEBVIEW");

//        input your facebook username and password
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("idorov01@gmail.com");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("qafocus02");

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