package AcceptanceTests;

import Utils.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

/**
 * Created by idorovskikh on 1/25/17.
 */
public class GoogleLogin extends BaseTest {

    @BeforeTest(groups = "acceptance")
    private void successfulGoogleLoginWithValidCredential(){
        System.out.println("login");
        driver.findElement(By.id("btnGoogleLogin")).click();

        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(By.id("android:id/button1")).click();

        Boolean result = elementIsNotPresent(By.id("com.android.packageinstaller:id/permission_allow_button"));

        if(!result){
            // driver.switchTo().alert().accept(); DOES NOT WORK!!!!
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }

        Assert.assertTrue(driver.findElementById("btnHamburger").isDisplayed());
    }

    @AfterMethod(groups = "acceptance")
    public void afterEachTest()  {
        System.out.println("Resetting App");
        driver.resetApp();
        successfulGoogleLoginWithValidCredential();
    }

}
