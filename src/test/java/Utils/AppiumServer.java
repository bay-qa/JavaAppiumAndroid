package Utils;

import java.io.IOException;

/**
 * Created by idorovskikh on 1/31/17.
 */
public class AppiumServer {
    public static void startAppiumServer() throws IOException, InterruptedException {

        Runtime.getRuntime().exec("appium");

        Thread.sleep(7000);
    }

    public static void stopAppiumServer() throws InterruptedException, IOException {

        Runtime.getRuntime().exec("killall node");

        Thread.sleep(3000);

    }
}
