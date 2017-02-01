package AcceptanceTests;

import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by idorovskikh on 1/31/17.
 */
public class Movies extends GoogleLogin {

    @Test(groups = "acceptance")
    public void verifyMoviesMarkedInterested() throws InterruptedException {
//      great reason why working with live data is always going to cause test flakiness
        Thread.sleep(10000);
        MobileElement firstMovie = (MobileElement)driver.findElementsById("rlItemFilm").get(0);
        // selecting first movie as Interested
        firstMovie.findElementById("tbButtonInterested").tap(1,0);

        // check if checkmark is present if any movie for current date has been marked as interested
        MobileElement currentDate = (MobileElement)driver.findElementsById("rl_date_picker_item").get(0);
        Assert.assertTrue(currentDate.findElementById("iv_movie_date_active_select").isDisplayed());

    }
}
