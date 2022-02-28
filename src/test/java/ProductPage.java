import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage{
    private static Logger logger = (Logger) LogManager.getLogger(BasePage.class);
    @Step("<repeat> kere kaydirma yap")
    public void scrollDown(int repeat) throws InterruptedException {//Scroll down using TouchAction Class

        Dimension size = appiumDriver.manage().window().getSize();
        TouchAction action = new TouchAction(appiumDriver);
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.width * 0.3);

        for (int i = 1; i <= repeat; i++) {
            action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(startX, endY))
                    .release()
                    .perform();
            Thread.sleep(2000);
        }
        logger.info("Sayfa kaydırma işlemi yapıldı");
    }
        @Step("Rastgele bir ürün seç")
        public void selectRandomproduct(){
            Random rdn = new Random();
            List<MobileElement> pd = findElements(By.xpath("//*[@resource-id='com.ozdilek.ozdilekteyim:id/imageView']"));
            System.out.println("pd"  +pd);
            MobileElement element =  pd.get(rdn.nextInt(pd.size()));
            System.out.println("element"  +element);
            element.click();
            logger.info("Rastgele ürün seçildi");
        }

    @Step("Rastgele bir beden seç")
    public void selectRandomSize(){
        Random rdn = new Random();
        List<MobileElement> pd = findElements(By.id("com.ozdilek.ozdilekteyim:id/tvInSizeItem"));
        System.out.println("pd"  +pd);
        MobileElement element =  pd.get(rdn.nextInt(pd.size()));
        System.out.println("element"  +element);
        element.click();
        logger.info("Rastgele beden seçildi");
    }

}

