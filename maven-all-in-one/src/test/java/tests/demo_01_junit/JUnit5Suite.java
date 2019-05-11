package tests.demo_01_junit;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@SuppressWarnings("JUnit5Platform")
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectClasses({JUnit5SimpleTests.class, JUnit5DataDrivenTests.class})
public class JUnit5Suite {
    /*
    Issue 1: Suites actually runs with Junit4, please read:
    https://stackoverflow.com/questions/46623128/junit-5-testsuite-alternative/50995287#50995287
    https://github.com/junit-team/junit5/issues/744

    Issue 2: Hooks does not work in suites
    The methods in your suite class will not be invoked this way.
    And you should be aware of the fact that suites and JUnit 5 are still work in progress.
    See: https://github.com/junit-team/junit5/issues/744
     */
}
