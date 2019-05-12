package tests.demo_05_selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import tests.demo_05_selenium.BasePage;

@SuppressWarnings("unused")
public class IssuesPage extends BasePage {

    @FindBy(xpath = "//*[@class=\"table-list-header-toggle states flex-auto pl-0\"]/a[2]")
    @CacheLookup
    private WebElement closedIssues;

    @FindBy(xpath = "//*[@class=\"table-list-header-toggle states flex-auto pl-0\"]/a[1]")
    @CacheLookup
    private WebElement openIssues;

    public IssuesPage(WebDriver driver) {
        super(driver);
        driver.navigate().to("https://github.com/dtopuzov/test/issues");
    }

    public int getClosedIssuesCount() {
        return Integer.parseInt(closedIssues.getText().split(" ")[0]);
    }

    public int getOpenIssuesCount() {
        return Integer.parseInt(openIssues.getText().split(" ")[0]);
    }
}
