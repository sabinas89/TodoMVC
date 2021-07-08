import drivers.DriverHandler;
import drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PositiveTests {

    DriverHandler driverHandler;
    WebDriver driver;

    @BeforeSuite
    public void initiateDriver() {
        driverHandler = DriverManager.getDriverHandler(DriverManager.DriverType.CHROME);
        driver = driverHandler.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Reporter.log("Tests running on Chrome Browser... <br>");
    }

    @Test
    public void urlIsWorking() {
        Reporter.log("Executing test to check URL is working... <br>");
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
        Reporter.log("Executing test to verify title... <br>");
        driver.get("https://todomvc.com/examples/angular2/");
        Reporter.log("Browser launched... <br>");
        Assert.assertEquals(driver.getTitle(), "Angular2 • TodoMVC");
        //driver.quit();
    }

    @Test
    public void addNewTask() throws InterruptedException {
        Reporter.log("Executing test to verify new task can be added... <br>");
        WebElement textBox = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".new-todo")));
        String text = "Note";
        textBox.sendKeys(text);
        Thread.sleep(500);
        textBox.sendKeys(Keys.ENTER);
        Reporter.log("New task added... <br>");
        String addedText = driver.findElement(By.xpath("(//label[contains(text()," + "'" + text + "')])")).getText();
        Assert.assertEquals(text, addedText);
    }

    @Test
    public void addMultipleTasks() throws InterruptedException {
        Reporter.log("Executing test to check Multiple todos can be added... <br>");
        WebElement textBox = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".new-todo")));
        // we will add 10 todos notes
        String text = "Notes";
        for (int i = 0; i < 10; i++) {
            textBox.sendKeys(text + " " + (i + 1));
            Thread.sleep(600);
            textBox.sendKeys(Keys.ENTER);
            Reporter.log("Added task number: " + (i + 1) + "<br>");
        }
        int count = Integer.valueOf(driver.findElement(By.tagName("strong")).getText());
        // This will also check the total number of items added till here
        Assert.assertEquals(count, 11);
    }

    @Test
    public void editTask() throws InterruptedException {
        Reporter.log("Executing test to verify task can be edited... <br>");
        Actions actions = new Actions(driver);
        List<WebElement> lists = driver.findElements(By.tagName("label"));
        WebElement note = lists.get(0);
        actions.moveToElement(note, 20, 20).doubleClick().build().perform();

        WebElement noteOne = driver.findElement(By.xpath("(//li[@class='editing'])"));
        actions.keyDown(noteOne, Keys.CONTROL);
        actions.sendKeys(noteOne, "a");
        actions.keyUp(noteOne, Keys.CONTROL);
        actions.build().perform();
        Thread.sleep(200);
        actions.sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(200);
        Reporter.log("Editing value for Todo item 1... <br>");
        actions.sendKeys("new value").build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();

        Assert.assertEquals(note.getText(), "new value");

    }

    @Test
    public void firstTaskFirst() {
        Reporter.log("Executing test to check task added first is visible at top... <br>");
        List<WebElement> lists = driver.findElements(By.tagName("label"));
        Assert.assertEquals(lists.get(0).getText(), "Note");
    }

    @Test
    public void taskInDiffLanguage() {
        Reporter.log("Executing test to check task can be added in different language... <br>");
        WebElement textBox = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".new-todo")));
        textBox.sendKeys("नमस्ते");
        textBox.sendKeys(Keys.ENTER);
        List<WebElement> lists = driver.findElements(By.tagName("label"));
        Assert.assertEquals(lists.get(lists.size() - 1).getText(), "नमस्ते");
    }

    @Test
    public void taskCompleted() throws InterruptedException {
        Reporter.log("Executing test to check multiple tasks can be marked as completed... <br>");
        List<WebElement> checkBox = driver.findElements(By.xpath("(//input[@class='toggle'])"));
        for (WebElement box : checkBox) {
            box.click();
            Thread.sleep(300);
        }
        int count = Integer.parseInt(driver.findElement(By.tagName("strong")).getText());
        Assert.assertEquals(count, 0);
        List<WebElement> completeCheck = driver.findElements(By.tagName("li"));
        for (WebElement webElement : completeCheck) {
            Assert.assertEquals(webElement.getAttribute("class"), "completed");
        }

    }

    @AfterSuite
    public void coolDown() {
        driver.quit();
    }

}
