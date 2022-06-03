package steps;

import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.open;
import static elements.BaseClass.initPage;

public class Hooks {
    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        initPage("Главная");
        open("https://www.google.com/");
    }
}
