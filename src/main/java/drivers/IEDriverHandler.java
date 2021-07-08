package drivers;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEDriverHandler extends DriverHandler {
    @Override
    protected void createDriver() {
        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
        this.driver = new InternetExplorerDriver();
    }
}
