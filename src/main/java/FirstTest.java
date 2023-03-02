import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\g.zvereva\\Desktop\\Project\\AppiumTestWikipedia\\src\\main\\resources\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitAndClickElement(By.id("org.wikipedia:id/search_container"));
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Doesn't work");
    }

    private void waitElement(By by, String error_message)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.withMessage(error_message);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    private void waitAndClickElement(By by){
        waitElement(by, "Element not found");
        driver.findElement(by).click();
    }

    private void assertElementHasText(By by, String errorMessage)
    {
        waitElement(by,"Element not found");
        WebElement el = driver.findElement(by);
        String search = el.getText();
        Assert.assertArrayEquals(errorMessage , "Search…".toCharArray(), search.toCharArray());
    }

}
