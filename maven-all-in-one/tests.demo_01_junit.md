**tests.demo_01_junit**

Show basics of JUnit testing.

Add JUnit dependency to pom.xml:
```
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.4.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.4.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite-api</artifactId>
            <version>1.4.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-runner</artifactId>
            <version>1.4.2</version>
            <scope>test</scope>
        </dependency>
```   
     
Test methods are annotated with @Test:
```
    @Test
    @Order(1)
    @DisplayName("Test Case 1")
    void testCase1() {
        int number1 = 1;
        int number2 = 2;
        assertTrue(number1 < number2, String.format("{%s} is not greater than {%s}", number2, number1));
    }
```

See demos in:
- tests.demo_01_junit.*

Resources:
- [Junit 5 Official Docs](https://junit.org/junit5/docs/current/user-guide/)
- [Junit 5 with Maven Tutorial](https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-running-unit-tests-with-maven/)
- [Junit 5 Migration Tips](https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4-tips)
- [Junit 4 Demos](https://github.com/dtopuzov/Demos/tree/master/src/test/java/tests/demo_01_junit)
