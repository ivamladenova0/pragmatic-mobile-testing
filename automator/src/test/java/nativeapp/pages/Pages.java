package nativeapp.pages;

/**
 * Enum with pages of wdio demo app.
 */
public enum Pages {
    HOME("Home"),
    WEB_VIEW("WebView"),
    LOGIN("Login"),
    FORMS("Forms"),
    SWIPE("Swipe");

    private String pageId;

    Pages(String pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return pageId;
    }
}
