package tests.demo_01_junit;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@SuppressWarnings("JUnit5Platform")
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectClasses(Junit5Tests.class)
public class Junit5Suite {
    /*
    The methods in your suite class will not be invoked this way.
    And you should be aware of the fact that suites and JUnit 5 are still work in progress.
    See: https://github.com/junit-team/junit5/issues/744
     */
}
