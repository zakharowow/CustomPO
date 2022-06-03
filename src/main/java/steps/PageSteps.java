package steps;

import elements.BaseClass;
import io.cucumber.java.bg.И;

public class PageSteps {

    @И("^загружается страница \"(.+)\"$")
    public void clickElement(String pageName) {
        BaseClass.initPage(pageName);
    }
}
