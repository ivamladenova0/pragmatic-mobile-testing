package tests.demo_01_junit;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
Those tests do not as part of mvn commandline run.
 */
public class JUnit4SimpleTests {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Executes only once before all tests in class.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Executes only once after all tests in class.");
    }

    @Before
    public void before() {
        System.out.println("Executes before each test in class.");
    }

    @After
    public void after() {
        System.out.println("Executes after each test in class.");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testCase1() {
        int number1 = 1;
        int number2 = 2;
        assertTrue(String.format("{%s} is not greater than {%s}", number2, number1), number1 < number2);
    }

    @Test(timeout = 1000)
    void testCase2() {
        String str1 = "Junit works fine.";
        String str2 = "Junit works fine.";
        assertEquals("String are not equal, something went wrong!", str1, str2);
    }

    @Test
    @Ignore("Disabled until bug #99 has been fixed")
    void testCase3() {
        String str1 = "Junit works fine.";
        String str2 = "Junit5 works fine.";
        assertEquals("String are not equal, something went wrong!", str1, str2);
    }
}
