package pages;

import base.BasePage;
import meta.Page;

public class AllPages {
    @Page("Главная")
    public BasePage mainPage = new MainPage();
    @Page("Результат поиска")
    public BasePage resultPage = new SearchResultPage();
    @Page("Яндекс")
    public BasePage yandexPage = new YandexPage();
}
