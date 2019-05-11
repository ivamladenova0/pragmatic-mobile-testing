package tests.demo_01_junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Class of Junit 5 Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Junit5Tests {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Executes only once before all tests in class.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Executes only once after all tests in class.");
    }

    @BeforeEach
    void before() {
        System.out.println("Executes before each test in class.");
    }

    @AfterEach
    void after() {
        System.out.println("Executes before each test in class.");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @Order(1)
    @DisplayName("Test Case 1")
    void testCase1() {
        int number1 = 1;
        int number2 = 2;
        assertTrue(number1 < number2, String.format("{%s} is not greater than {%s}", number2, number1));
    }

    @Test
    @Order(2)
    @DisplayName("Test Case 2")
    void testCase2() {
        String str1 = "Junit works fine.";
        String str2 = "Junit works fine.";
        assertEquals(str1, str2, "String are not equal, something went wrong!");
    }

    @Test
    @Order(3)
    @DisplayName("Test Case 3")
    @Disabled("Disabled until bug #99 has been fixed")
    void testCase3() {
        String str1 = "Junit works fine.";
        String str2 = "Junit5 works fine.";
        assertEquals(str1, str2, "String are not equal, something went wrong!");
    }
}
