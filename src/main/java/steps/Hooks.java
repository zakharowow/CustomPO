package steps;

import base.ScenarioDataStore;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static base.ScenarioDataStore.setCurrentPage;
import static com.codeborne.selenide.Selenide.*;

public class Hooks {
    ScenarioDataStore scenarioDataStore = new ScenarioDataStore();
    @Before
    public void before(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        scenarioDataStore.initScenarioData(scenario);
        open("https://habr.com/ru/search/");
        setCurrentPage("Главная");
    }
}
