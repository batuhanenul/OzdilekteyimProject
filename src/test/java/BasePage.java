import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static io.appium.java_client.touch.offset.PointOption.point;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage extends BaseTest{
    Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(appiumDriver)
            .withTimeout(30, SECONDS)
            .pollingEvery(5, SECONDS)
            .ignoring(NoSuchElementException.class);
    private static Logger logger = (Logger) LogManager.getLogger(BasePage.class);

    @Step("<time> saniye bekle")
    public void waitForSecond(int time) throws InterruptedException {
        int waitTime = 1000*time;
       Thread.sleep(1000 * time);
        logger.info(waitTime+" saniye statik bekleme yapıldı");
    }
    @Step("id <id> li elemana tıkla")
    public void clickByID(String id){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
        findElement(By.id(id)).click();
        logger.info("id: " + id + " li elemente tıklandı");
    }

    @Step("<id> li alana <text> bilgisini gir")
    public void sendKeysID(String id, String text){
    MobileElement element = appiumDriver.findElement(By.id(id));
    element.sendKeys(text);
    logger.info(id + " li alana " + text + " bilgisi girildi");
    }

    @Step("xpath <elementxpath> li elemana tıkla")
    public void clickByXpath(String elementXPath){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elementXPath)));
        appiumDriver.findElement(By.xpath(elementXPath)).click();
        logger.info("xpath: " + elementXPath + " li elemente tıklandı");
    }



    @Step("<key> id'li element var mı?")
    public void isDisplayed(String key) {

        Assert.assertTrue("Element bulunamadı", findElement(By.id(key)).isDisplayed());
        String strLogger = appiumDriver.findElement(By.id(key)).getText();
        logger.info(strLogger + " id li element var");
    }


    public MobileElement findElement(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return appiumDriver.findElement(by);
    }

    public List<MobileElement> findElements(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return appiumDriver.findElements(by);
    }
}


