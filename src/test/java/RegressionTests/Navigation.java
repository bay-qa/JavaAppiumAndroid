package RegressionTests;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by idorovskikh on 1/25/17.
 */
public class Navigation extends FacebookLogin {

    private MobileElement tabItem(int index) {
        List<MobileElement> tabs = driver.findElementsById("tvTitle");
        return tabs.get(index);
    }

    @Test(groups = "regression")
    protected void navigateThroughLandingScreens(){
        WebElement moviesScreen = driver.findElementById("pager");
        Assert.assertTrue(moviesScreen.isDisplayed());

        List<MobileElement> tabs = driver.findElementsById("tvTitle");

        tabItem(1).click();

        WebElement matesScreen = driver.findElementById("rlMatesPage");
        Assert.assertTrue(matesScreen.isDisplayed());

        tabItem(2).click();
        WebElement chatsScreen = driver.findElementById("rlChatsContent");

        Assert.assertTrue(chatsScreen.isDisplayed());

    }
}
