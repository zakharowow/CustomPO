package elements;

import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BaseClass {
    @PageElement("Логотип")
    public SelenideElement logo = $(byAttribute("alt", "Google"));
    @PageElement("Поисковая строка")
    public SelenideElement searchTextField = $(byXpath("(//*[@name='q'])[1]"));
    @PageElement("Кнопка поиска")
    public SelenideElement searchButton = $(byAttribute("aria-label", "Поиск в Google"));
}
