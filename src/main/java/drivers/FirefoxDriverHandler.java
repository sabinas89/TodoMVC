package drivers;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverHandler extends DriverHandler {

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        this.driver = new FirefoxDriver();
    }
}
