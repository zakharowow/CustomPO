package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage implements BasePage {
    @PageElement(value = "Сортировка результатов", isLoaded = true)
    public final SelenideElement quantity = $x("(//*[@class='tm-navigation-dropdown__button tm-navigation-dropdown__button'])[last()]");

    @PageElement("Результат поиска")
    public final ElementsCollection searchResults = $$(byClassName("tm-articles-list__item"));

}
