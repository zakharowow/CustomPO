package steps;

import base.ScenarioDataStore;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static base.ScenarioDataStore.setCurrentPage;
import static com.codeborne.selenide.Selenide.open;

public class Hooks {
    @Before
    public void before(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        open("https://habr.com/ru/search/");
        ScenarioDataStore scenarioDataStore = new ScenarioDataStore();
        scenarioDataStore.initScenarioData(scenario);
        setCurrentPage("Главная");
//        FBasePage fBasePage = new FMainPage();
//        fBasePage.getElement("Логотип");
//        pageShouldBeLoaded("Главная");
    }
}
