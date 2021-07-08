import drivers.DriverHandler;
import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PositiveTests {

    DriverHandler driverHandler;
    WebDriver driver;

    @Test
    public void urlIsWorking() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://todomvc.com/examples/angular2/").openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            Assert.assertEquals(status, 200);
            Reporter.log("Launching browser...");
        } catch (MalformedURLException | ProtocolException e) {
            Reporter.log(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyTitle() {
        Reporter.log("Executing test to verify title... \n");
        driverHandler = DriverManager.getDriverHandler(DriverManager.DriverType.CHROME);
        driver = driverHandler.getDriver();
        driver.get("https://todomvc.com/examples/angular2/");
        Reporter.log("Launched browser");
        Assert.assertEquals(driver.getTitle(), "Angular2 • TodoMVC");
    }

}
