package steps;

import io.cucumber.java.bg.И;

import static base.BasePage.initPage;

public class PageSteps {

    @И("^загружается страница \"(.+)\"$")
    public void loadPage(String pageName) {
        initPage(pageName);
    }
}
