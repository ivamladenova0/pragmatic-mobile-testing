package tests.demo_05_selenium.Tests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.demo_05_selenium.API.Api;
import tests.demo_05_selenium.BaseTest;
import tests.demo_05_selenium.Pages.DefaultPage;
import tests.demo_05_selenium.Pages.IssuesPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GitHubTests extends BaseTest {

    @Test
    public void test_01_navigation() {
        DefaultPage home = new DefaultPage(driver);
        home.navigateTo("Issues");
        home.navigateTo("Pull requests");
        home.navigateTo("Projects");
    }

    @Test
    public void test_02_verifyOpenAreLessThanClosed() {
        DefaultPage home = new DefaultPage(driver);
        IssuesPage issues = home.navigateToIssues();
        int open = issues.getOpenIssuesCount();
        int closed = issues.getClosedIssuesCount();
        System.out.println("Open: " + open);
        System.out.println("Closed: " + closed);
        assertTrue("Open issues are more than closed.", open < closed);
    }

    @Test
    public void test_03_verifyClosedAndOpenCount() {
        // Get open and closed count via UI
        IssuesPage issues = new IssuesPage(driver);
        int openUI = issues.getOpenIssuesCount();
        int closedUI = issues.getClosedIssuesCount();

        // Get open and closed count via API
        int openAPI = Api.getOpenIssues("dtopuzov/test");
        int closedAPI = Api.getClosedIssues("dtopuzov/test");

        assertEquals(openUI, openAPI);
        assertEquals(closedUI, closedAPI);
    }
}
