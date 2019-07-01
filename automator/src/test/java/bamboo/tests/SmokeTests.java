package bamboo.tests;

import bamboo.pages.IntroPage;
import bamboo.pages.LibraryPage;
import base.MobileTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class SmokeTests extends MobileTest {

    @Test
    public void singUp() throws InterruptedException, IOException, URISyntaxException {
        IntroPage introPage = new IntroPage(driver);
        introPage.openLibrary();
        LibraryPage libraryPage = new LibraryPage(driver);
        libraryPage.changeCover("yellow_circle.png");
        libraryPage.verifyBackground("yellow_background.png");
    }
}
