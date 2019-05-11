package tests.demo_01_junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Data Driven Tests")
class JUnit5DataDrivenTests {

    @ParameterizedTest
    @ValueSource(strings = {"Audi", "BMW", "Mercedes"})
    void inputShouldBeShort(String input) {
        assertTrue(input.length() >= 3, "Input string is too short.");
    }
}
