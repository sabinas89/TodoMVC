package drivers;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverHandler extends DriverHandler {
    @Override
    protected void createDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        this.driver = new ChromeDriver();
    }
}
