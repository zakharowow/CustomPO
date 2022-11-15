package steps;

import io.cucumber.java.Before;

import static base.BasePage.pageShouldBeLoaded;
import static com.codeborne.selenide.Selenide.open;

public class Hooks {
    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        open("https://www.google.com/");
        pageShouldBeLoaded("Главная");
    }
}
