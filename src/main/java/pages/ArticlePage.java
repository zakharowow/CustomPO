package pages;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class ArticlePage extends FramePage implements BasePage {
    @PageElement(value = "Текст статьи", isLoaded = true)
    private final SelenideElement articleBody = $(byClassName("tm-article-body"));
}
