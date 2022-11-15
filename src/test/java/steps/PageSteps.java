package steps;

import io.cucumber.java.bg.И;

import static base.BasePage.pageShouldBeLoaded;

public class PageSteps {

    @И("^загружается страница \"(.+)\"$")
    public void loadPage(String pageName) {
        pageShouldBeLoaded(pageName);
    }
}
