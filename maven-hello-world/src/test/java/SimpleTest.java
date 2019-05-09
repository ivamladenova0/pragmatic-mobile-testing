import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleTest {

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test
    public void testMethod1() {
        Reporter.log("Test #1");
        Assert.assertTrue(true);
    }

    @Test
    public void testMethod2() {
        Reporter.log("Test #2");
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testMethod3() {
        Reporter.log("Test #3");
        Assert.assertEquals(1, 2);
    }
}
