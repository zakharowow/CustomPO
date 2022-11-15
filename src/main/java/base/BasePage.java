package base;

import com.codeborne.selenide.SelenideElement;
import pages.AllPages;
import meta.Page;
import meta.PageElement;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static base.BaseWebElement.getElement;
import static com.codeborne.selenide.Condition.visible;
import static java.lang.String.format;

public class BasePage {
    static Map<String, Object> currentPage = new HashMap<>();

    public static Map<String, Object> getCurrentPage() {
        return currentPage;
    }

    public static void pageShouldBeLoaded(String pageName) {
        if (currentPage.entrySet().isEmpty() || !currentPageName().equals(pageName)) {
            initPage(pageName);
        }
        SelenideElement isLoadedElement = getElement("isLoaded", true);
        if (isLoadedElement != null) {
            isLoadedElement.shouldBe(visible);
        } else {
            System.out.println(format("WARNING: На странице [%s] нет привязки к загрузке элементов", pageName));
        }
    }

    private static String currentPageName() {
        return currentPage.entrySet().iterator().next().getKey();
    }

    private static void initPage(String pageName) {
        HashSet<Object> pagesSet = new HashSet<>();
        Class<?> clazz = AllPages.class;
        Field[] pages = clazz.getDeclaredFields();
        BasePage pageToInit = null;
        for (Field page : pages) {
            String annotationValue = page.getAnnotation(Page.class).value();
            pagesSet.add(annotationValue);
            try {
                if (annotationValue.equals(pageName)) {
                    pageToInit = (BasePage) page.get(clazz.newInstance());
                    initPageElements(pageName, pageToInit);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(format("Описание страницы [%s] отсутствует в классе [%s]", pageName, AllPages.class.getName()), pageToInit);
        Assert.assertEquals(format("В классе [%s] присутствуют аннотации дубликаты", AllPages.class.getName()), pagesSet.size(), pages.length);
    }

    private static void initPageElements(String pageName, BasePage basePage) {
        currentPage.clear();
        HashSet<Object> fieldsSet = new HashSet<>();
        Map<String, Object> elements = new HashMap<>();
        Class<? extends BasePage> clazz = basePage.getClass();
        Field[] elementsOnPage = clazz.getDeclaredFields();
        for (Field field : clazz.getDeclaredFields()) {
            String annotationValue = field.getAnnotation(PageElement.class).value();
            fieldsSet.add(annotationValue);
            try {
                elements.put(annotationValue, field.get(clazz.newInstance()));
                if (field.getAnnotation(PageElement.class).isLoaded()) {
                    elements.put("isLoaded", field.get(clazz.newInstance()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(format("На странице [%s] отсутствует описание элементов", pageName), elements.size() > 0);
        Assert.assertEquals(format("На странице [%s] присутствуют аннотации дубликаты", pageName), fieldsSet.size(), elementsOnPage.length);
        currentPage.put(pageName, elements);
    }
}
