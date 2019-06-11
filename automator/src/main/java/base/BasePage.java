package base;

import enums.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import logger.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utils.ImageUtils;
import utils.ImageVerificationResult;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Base mobile page.
 */
public class BasePage {
    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Nullable
    private String getWebContext(AppiumDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }

    public void switchToWebContext() throws Exception {
        String webContext = this.getWebContext(driver);
        if (webContext != null) {
            driver.context(webContext);
        } else {
            throw new Exception("Failed to switch to WebView context.");
        }
    }

    public void switchToNativeContext() {
        driver.context("NATIVE_APP");
    }

    public void swipe(SwipeDirection direction, int duration) {
        Dimension size = driver.manage().window().getSize();
        int centerX = (int) (size.width * 0.5);
        int centerY = (int) (size.height * 0.5);
        int startX = centerX;
        int startY = centerY;
        int endX = centerX;
        int endY = centerY;

        if (direction == SwipeDirection.UP) {
            startY = (int) (size.height * 0.7);
            endY = (int) (size.height * 0.3);
        } else if (direction == SwipeDirection.DOWN) {
            startY = (int) (size.height * 0.3);
            endY = (int) (size.height * 0.7);
        } else if (direction == SwipeDirection.LEFT) {
            startX = (int) (size.width * 0.7);
            endX = (int) (size.width * 0.3);
        } else if (direction == SwipeDirection.RIGHT) {
            startX = (int) (size.width * 0.3);
            endX = (int) (size.width * 0.7);
        }

        TouchAction action = new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(endX, endY))
                .release();
        action.perform();
        Reporter.log(String.format("Swipe %s.", direction));
    }

    public void swipe(SwipeDirection direction) {
        this.swipe(direction, 1000);
    }

    public void match(String image, double tolerance, int timeout) throws Exception {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage actualImage = ImageIO.read(screenshot);
        BufferedImage expectedImage = actualImage;
        ImageVerificationResult result = ImageUtils.compare(actualImage, expectedImage, 10, 0, 0);
        if (result.diffPercent <= tolerance) {
            Log.info(String.format("Current screen matches %s.", image));
        } else {
            Log.error(String.format("Current screen does not match %s.", image));
        }
    }

    public void match(String image, double tolerance) throws Exception {
        this.match(image, tolerance, 30);
    }

    public void match(String image) throws Exception {
        this.match(image, 0.01);
    }
}
