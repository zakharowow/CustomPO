package steps;

import base.ScenarioDataStore;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;

import static base.ScenarioDataStore.setCurrentPage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class Hooks {
    ScenarioDataStore scenarioDataStore = new ScenarioDataStore();

    @Before
    public void before(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        scenarioDataStore.initScenarioData(scenario);
        open("https://habr.com/ru/search/");
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(500,768));
        setCurrentPage("Главная");
    }
}
