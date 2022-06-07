package steps;

import io.cucumber.java.bg.И;

import static elements.BaseClass.initPage;

public class PageSteps {

    @И("^загружается страница \"(.+)\"$")
    public void loadPage(String pageName) {
        initPage(pageName);
    }
}
