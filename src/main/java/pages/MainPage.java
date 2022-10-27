package pages;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    @PageElement("Логотип")
    public SelenideElement logo = $(byAttribute("class","k1zIA rSk4se"));
    @PageElement("Поисковая строка")
    public SelenideElement searchTextField = $(byXpath("(//*[@name='q'])[1]"));
    @PageElement("Кнопка поиска")
    public SelenideElement searchButton = $(byAttribute("aria-label", "Поиск в Google"));
}
