package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends BasePage {
    @PageElement("Результат поиска")
    public ElementsCollection searchResults = $$(byXpath("//div//h3[not(.//@class='l' or .//@class='q8U8x' or .//text()='Описание')]"));

    @PageElement("Количество результатов")
    public SelenideElement quantity = $(byId("result-stats"));
}
