package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YandexPage extends BaseClass {

    @PageElement("Закрытие модального окна")
    public SelenideElement closeModal = $(byClassName("modal__close"));

    @PageElement("Строка поиска")
    public SelenideElement searchField = $(byAttribute("aria-label", "Запрос"));

    @PageElement("Найти")
    public SelenideElement searchButton = $(byXpath("//button[.//text()='Найти']"));

    @PageElement("Результат поиска")
    public ElementsCollection searchResult = $$(byXpath("//*[@class='OrganicTitleContentSpan organic__title']"));
}
