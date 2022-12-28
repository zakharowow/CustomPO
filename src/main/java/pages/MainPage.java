package pages;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage implements BasePage {
    @PageElement("Логотип")
    private final SelenideElement logo = $x("//*[@id='app']/div[1]/header/div/div/span");

    @PageElement(value = "Поисковая строка", isLoaded = true)
    private final SelenideElement searchTextField = $x("//*[@id='app']/div[1]/div[2]/main/div/div/div/div[1]/div/div[2]/form/div/input");

    @PageElement("Кнопка поиска")
    private final SelenideElement searchButton = $x("//*[@id='app']/div[1]/div[2]/main/div/div/div/div[1]/div/div[2]/form/div/div/span");

}
