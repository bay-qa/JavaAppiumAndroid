package AcceptanceTests;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

/**
 * Created by idorovskikh on 1/25/17.
 */
public class GoogleLogin extends BaseTest {

    @BeforeTest(groups = "acceptance")
    private void successfulGoogleLoginWithValidCredential() {
        driver.findElement(By.id("btnGoogleLogin")).click();

        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(By.id("android:id/button1")).click();
        Assert.assertTrue(driver.findElementById("btnHamburger").isDisplayed());

    }
}
