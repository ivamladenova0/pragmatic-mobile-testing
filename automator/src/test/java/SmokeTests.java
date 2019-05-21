import base.MobileTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmokeTests extends MobileTest {

    @Test
    void testAddMaxInteger() {
        assertEquals(2147483646, Integer.sum(2147183646, 300000));
    }

    @Test
    void testDivide() {
        assertEquals(2, Integer.divideUnsigned(4, 2));
    }

}
