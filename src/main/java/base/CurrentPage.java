package base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.Page;
import meta.PageElement;
import pages.AllPages;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CurrentPage implements BasePage {
    private final HashMap<String, Object> currentPage;

    public CurrentPage(String pageName) {
        this.currentPage = getPageElements(pageName);
        pageShouldBeLoaded();
    }

    public SelenideElement getSelenideElement(String fieldName) {
        return (SelenideElement) getWebObject(fieldName);
    }

    public ElementsCollection getElementsCollection(String collectionName) {
        return (ElementsCollection) getWebObject(collectionName);
    }

    public String getCollectionElementSubPath(String pathName) {
        return (String) getWebObject(pathName);
    }

    private Object getWebObject(String elementName) {
        return this.currentPage.get(elementName);
    }

    private HashMap<String, Object> getPageElements(String pageName) {
        HashMap<String, Object> elements = new HashMap<>();
        Class<? extends BasePage> clazz = getPage(pageName).getClass();
        if (!clazz.getSuperclass().equals(Object.class)) {
            elements.putAll(setElements(clazz.getSuperclass(), elements));
        }
        elements.putAll(setElements(clazz, elements));
        assertTrue(format("На странице [%s] отсутствует описание элементов", pageName), elements.size() > 0);
        return elements;
    }

    private HashMap<String, Object> setElements(Class<?> clazz, HashMap<String, Object> elements) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String annotationValue = field.getAnnotation(PageElement.class).value();
            try {
                elements.put(annotationValue, field.get(clazz.newInstance()));
                if (field.getAnnotation(PageElement.class).isLoaded()) {
                    elements.put("isLoaded", field.get(clazz.newInstance()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return elements;
    }

    private BasePage getPage(String pageName) {
        Class<?> clazz = AllPages.class;
        Field[] pages = clazz.getDeclaredFields();
        BasePage result = null;
        for (Field page : pages) {
            page.setAccessible(true);
            String annotationValue = page.getAnnotation(Page.class).value();
            try {
                if (annotationValue.equals(pageName)) {
                    result = (BasePage) page.get(clazz.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assertNotNull(format("Описание страницы [%s] отсутствует в классе [%s]", pageName, AllPages.class.getName()), result);
        return result;
    }

    private void pageShouldBeLoaded() {
        Object isLoadedElement = this.getWebObject("isLoaded");
        if (isLoadedElement instanceof SelenideElement) {
            ((SelenideElement) isLoadedElement).shouldBe(visible);
        } else {
            ((ElementsCollection) isLoadedElement).first().shouldBe(visible);
        }
    }
}
