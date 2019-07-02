package bamboo.tests;

import bamboo.enums.NoteType;
import bamboo.pages.IntroPage;
import bamboo.pages.LibraryPage;
import base.MobileTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class SmokeTests extends MobileTest {
    LibraryPage libraryPage;

    @BeforeClass
    public void beforeNotesTests() throws InterruptedException {
        IntroPage introPage = new IntroPage(driver);
        introPage.openLibrary();
        libraryPage = new LibraryPage(driver);
    }

    @Test
    public void editNoteColor() throws InterruptedException, IOException, URISyntaxException {
        libraryPage.changeCover("yellow_circle.png");
        libraryPage.verifyBackground("yellow_background.png");
        libraryPage.save();
    }

    @Test
    public void addNewNote() {
        libraryPage.addNote(NoteType.GRAYSCALE);
    }
}
