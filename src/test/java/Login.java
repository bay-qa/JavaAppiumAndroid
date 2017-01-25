import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by idorovskikh on 1/17/17.
 */
public class Login extends BaseTest{

    @Test
    public void successfulFacebookLoginWithValidCredential() throws InterruptedException {
        driver.findElement(By.id("btnLogin")).click();

        changeContext("WEBVIEW");

//        input your facebook username and password
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