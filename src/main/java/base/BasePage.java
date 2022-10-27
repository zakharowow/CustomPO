package base;

import pages.AllPages;
import meta.Page;
import meta.PageElement;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BasePage {
    static Map<String, Object> currentPage = new HashMap<>();

    public static Map<String, Object> getCurrentPage(){
        return currentPage;
    }

    public static void initPage(String pageName) {
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
        Assert.assertNotNull(String.format("Описание страницы [%s] отсутствует в классе [%s]", pageName, AllPages.class.getName()), pageToInit);
        Assert.assertEquals(String.format("В классе [%s] присутствуют аннотации дубликаты", AllPages.class.getName()), pagesSet.size(), pages.length);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(String.format("На странице [%s] отсутствует описание элементов", pageName), elements.size() > 0);
        Assert.assertEquals(String.format("На странице [%s] присутствуют аннотации дубликаты", pageName), fieldsSet.size(), elementsOnPage.length);
        currentPage.put(pageName, elements);
    }
}
