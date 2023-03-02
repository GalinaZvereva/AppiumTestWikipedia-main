import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class ThirdTest {
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
        capabilities.setCapability("app","C:\\Users\\g.zvereva\\Desktop\\Project\\appiumTestWikipedia\\src\\main\\resources\\org.wikipedia.apk");
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
        waitAndFillText(By.id("org.wikipedia:id/search_src_text"), "Newspaper");
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='Newspaper']"));
        waitAndClickElement(By.xpath("//android.widget.ImageView[@content-desc='More options']"));
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='Add to reading list']"));
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='GOT IT']"));
        waitAndClear(By.id("org.wikipedia:id/text_input"));
        waitAndFillText(By.id("org.wikipedia:id/text_input"), "My favourite list");
        waitAndClickElement(By.id("android:id/button1"));
        waitAndClickElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"));
        waitAndClickElement(By.id("org.wikipedia:id/search_container"));
        waitAndFillText(By.id("org.wikipedia:id/search_src_text"), "Romeo");
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='Romeo and Juliet']"));
        waitAndClickElement(By.xpath("//android.widget.ImageView[@content-desc='More options']"));
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='Add to reading list']"));
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='My favourite list']"));
        waitAndClickElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"));
        waitAndClickElement(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"));
        waitAndClickElement(By.xpath("//android.widget.TextView[@text='My favourite list']"));
        longPressElement(By.xpath("//android.widget.TextView[@text='Romeo and Juliet']"));
        waitAndClickElement(By.id("org.wikipedia:id/menu_delete_selected"));
        assertCountOfArticles();
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
    private void waitAndFillText(By by, String text){
        waitElement(by, "Element not found");
        driver.findElement(by).sendKeys(text);
    }
    private void waitAndClear(By by){
        waitElement(by, "Element not found");
        driver.findElement(by).clear();
    }
    private void longPressElement(By by){
        waitElement(by, "Cant found this element");
        TouchAction longPress = new TouchAction<>(driver);
        WebElement article = driver.findElement(by);
        longPress.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(article))).perform();
    }
    private void assertCountOfArticles(){
        WebElement element = driver.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/item_reading_list_statistical_description']"));
        System.out.println(element.getText());
        Assert.assertTrue("You have more than one articles", element.getText().contains("1 of 1"));
    }

}
