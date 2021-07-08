import drivers.DriverHandler;
import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NegativeTests {

    DriverHandler driverHandler;
    WebDriver driver;

    @BeforeSuite
    public void initiateDriver() {
        driverHandler = DriverManager.getDriverHandler(DriverManager.DriverType.CHROME);
        driver = driverHandler.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Reporter.log("Tests running on Chrome Browser... <br>");
    }

    @Test(priority = 1)
    public void noBlankTodo() {
        Reporter.log("Executing test to check no blank tasks can be added... <br>");
        driver.get("https://todomvc.com/examples/angular2/");
        Reporter.log("Browser is launched... <br>");
        WebElement textBox = driver.findElement(By.cssSelector(".new-todo"));
        textBox.sendKeys(Keys.SPACE);
        textBox.sendKeys(Keys.SPACE);
        textBox.sendKeys(Keys.ENTER);
        List<WebElement> count = driver.findElements(By.tagName("strong"));
        Assert.assertEquals(count.size(), 0);
    }

    @Test(priority = 2)
    public void duplicateTasks() throws InterruptedException {
        Reporter.log("Executing test to check duplicate tasks can be added... <br>");
        driver.navigate().refresh();
        Thread.sleep(300);
        WebElement textBox = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".new-todo")));
        textBox.sendKeys("Task");
        textBox.sendKeys(Keys.ENTER);
        textBox.sendKeys("Task");
        textBox.sendKeys(Keys.ENTER);
        List<WebElement> taskList = driver.findElements(By.tagName("label"));
        Assert.assertEquals(taskList.size(), 2);
        Assert.assertEquals(taskList.get(0).getText(), taskList.get(1).getText());
    }

    @Test(priority = 3)
    public void specialCharacters() {
        Reporter.log("Executing test to check special characters are allowed... <br>");
        WebElement textBox = driver.findElement(By.cssSelector(".new-todo"));
        textBox.sendKeys("%$#&");
        textBox.sendKeys(Keys.ENTER);
        List<WebElement> taskList = driver.findElements(By.tagName("label"));
        Assert.assertEquals(taskList.get(taskList.size() - 1).getText(), "%$#&");
    }

    @Test(priority = 4)
    public void pageRefreshCheck() {
        Reporter.log("Executing test to check that todo items exist even after page refresh... <br>");
        int count = Integer.parseInt(driver.findElement(By.tagName("strong")).getText());
        driver.navigate().refresh();
        int countPostRef = Integer.parseInt(driver.findElement(By.tagName("strong")).getText());
        Assert.assertEquals(count, countPostRef);
    }

    @Test(priority = 5)
    public void newSession() {
        Reporter.log("Executing test to check that todo task list is not visible in new browser session... <br>");
        DriverHandler newDriverHandler = DriverManager.getDriverHandler(DriverManager.DriverType.CHROME);
        WebDriver newDriver = newDriverHandler.getDriver();
        newDriver.get("https://todomvc.com/examples/angular2/");
        List<WebElement> count = newDriver.findElements(By.tagName("strong"));
        Assert.assertEquals(count.size(), 0);
        newDriver.quit();
    }

    @AfterSuite
    public void coolDown() {
        driver.quit();
    }
}
