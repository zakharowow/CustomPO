package steps;

import io.cucumber.java.bg.И;

import static base.ScenarioDataStore.setCurrentPage;

public class PageSteps {

    @И("^загружается страница \"(.+)\"$")
    public void loadPage(String pageName) {
        setCurrentPage(pageName);
    }
}
