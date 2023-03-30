package pages;

import base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.PageElement;

import static com.codeborne.selenide.Selenide.*;

public class FramePage implements BasePage {
    @PageElement("Бутерброд")
    protected final SelenideElement burger = $x("//button[contains(@class,'button_burger')]");

    @PageElement("Меню бутерброда")
    protected final ElementsCollection searchResults = $$x("//*[@class='tm-burger-menu']//a");

}
