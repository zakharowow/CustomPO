package elements;

import com.codeborne.selenide.ElementsCollection;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends BaseClass {
    @PageElement("Результат поиска")
    public ElementsCollection searchResults = $$(byXpath("//div//h3[not(.//@class='l' or .//@class='q8U8x' or .//text()='Описание')]"));
}
