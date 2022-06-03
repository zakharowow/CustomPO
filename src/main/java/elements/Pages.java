package elements;

import meta.Page;

public class Pages extends BaseClass {
    @Page("Главная")
    public BaseClass mainPage = new MainPage();
    @Page("Результат поиска")
    public BaseClass resultPage = new SearchResultPage();
    @Page("Яндекс")
    public BaseClass yandexPage = new YandexPage();
}
