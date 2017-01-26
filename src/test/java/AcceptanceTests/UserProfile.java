package AcceptanceTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by idorovskikh on 1/18/17.
 */
public class UserProfile extends GoogleLogin {

    @Test(groups = "acceptance")
    public void changeName() {
        driver.findElementById("btnHamburger").click();
        driver.findElementById("ivName").click();

        driver.findElementById("edit_text").clear();
        driver.findElementById("edit_text").sendKeys("Jeff");
        driver.hideKeyboard();
        By button = By.id("android:id/button1");
        driver.findElement(button).click();
        WebElement nameField = driver.findElementById("tvNameValue");
        String actualResult = nameField.getText();
        Assert.assertEquals(actualResult, "Jeff");
    }

}
