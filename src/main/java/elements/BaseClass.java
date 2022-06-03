package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.Page;
import meta.PageElement;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.*;

public class BaseClass {
    static Map<String, Object> currentPage = new HashMap<>();

    public static SelenideElement getField(String fieldName) {
        return (SelenideElement) getElement(fieldName);
    }

    public static ElementsCollection getCollection(String collectionName) {
        return (ElementsCollection) getElement(collectionName);
    }
    public static void initPage(String pageName) {
        HashSet<Object> pagesSet = new HashSet<>();
        Class<?> clazz = Pages.class;
        Field[] pages = clazz.getDeclaredFields();
        BaseClass pageToInit = null;
        for (Field page : pages) {
            String annotationValue = page.getAnnotation(Page.class).value();
            pagesSet.add(annotationValue);
            try {
                if (annotationValue.equals(pageName)) {
                    pageToInit = (BaseClass) page.get(clazz.newInstance());
                    initPageElements(pageName, pageToInit);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(String.format("Описание страницы [%s] отсутствует в классе Pages", pageName), pageToInit);
        Assert.assertEquals("В классе Pages присутствуют аннотации дубликаты", pagesSet.size(), pages.length);
    }

    private static void initPageElements(String pageName, BaseClass baseClass) {
        currentPage.clear();
        HashSet<Object> fieldsSet = new HashSet<>();
        Map<String, Object> elements = new HashMap<>();
        Class<? extends BaseClass> clazz = baseClass.getClass();
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
        Assert.assertEquals(String.format("На странице %s присутствуют аннотации дубликаты", pageName), fieldsSet.size(), elementsOnPage.length);
        currentPage.put(pageName, elements);
    }

    private static Object getElement(String elementName) {
        String pageName = currentPage.keySet().iterator().next();
        Object pageElements = currentPage.get(pageName);
        Object element = ((HashMap<?, ?>) pageElements).get(elementName);
        Assert.assertNotNull(String.format("На странице [%s] отсутствует описание элемента [%s]", elementName, pageName), element);
        return element;
    }

}
