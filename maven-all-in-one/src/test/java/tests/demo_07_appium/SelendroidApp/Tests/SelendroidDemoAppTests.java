package tests.demo_07_appium.SelendroidApp.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.demo_07_appium.SelendroidApp.Appium.Client;
import tests.demo_07_appium.SelendroidApp.Hooks.BaseTest;
import tests.demo_07_appium.SelendroidApp.Pages.HomePage;

public class SelendroidDemoAppTests extends BaseTest {

    @Test
    public void checkBox() {
        HomePage seleniumDemo = new HomePage(Client.driver);
        seleniumDemo.checkBox.click();
        boolean isChecked = Boolean.valueOf(seleniumDemo.checkBox.getAttribute("checked"));
        Assert.assertFalse(isChecked, "Checkbox is still checked.");
        seleniumDemo.checkBox.click();
        isChecked = Boolean.valueOf(seleniumDemo.checkBox.getAttribute("checked"));
        Assert.assertTrue(isChecked, "Checkbox is unchecked.");
    }

    @Test
    public void displayTextView() {
        HomePage seleniumDemo = new HomePage(Client.driver);
        seleniumDemo.displayTextViewButton.click();

        // Alternatively you can use
        // seleniumDemo.findButton("Display text view").click();
    }
}
