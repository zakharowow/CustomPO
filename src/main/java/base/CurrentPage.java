package base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import meta.Page;
import meta.PageElement;
import pages.AllPages;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

import static com.codeborne.selenide.Condition.visible;
import static java.lang.String.format;
import static org.junit.Assert.*;

public class CurrentPage implements BasePage {
    private final HashMap<String, Object> currentPage;
    private final String currentPageName;

    public CurrentPage(String pageName) {
        this.currentPageName = pageName;
        this.currentPage = getPageElements(pageName);
        pageShouldBeLoaded();
    }

    public SelenideElement getElement(String fieldName) {
        return (SelenideElement) getWebObject(fieldName);
    }

    public ElementsCollection getCollection(String collectionName) {
        return (ElementsCollection) getWebObject(collectionName);
    }

    private Object getWebObject(String elementName) {
        return this.currentPage.get(elementName);
    }

    private HashMap<String, Object> getPageElements(String pageName) {
        HashSet<Object> fieldsSet = new HashSet<>();
        HashMap<String, Object> elements = new HashMap<>();
        Class<? extends BasePage> clazz = getPage(pageName).getClass();
        Field[] elementsOnPage = clazz.getDeclaredFields();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
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
        assertTrue(format("На странице [%s] отсутствует описание элементов", pageName), elements.size() > 0);
        assertEquals(format("На странице [%s] присутствуют аннотации дубликаты", pageName), fieldsSet.size(), elementsOnPage.length);
        return elements;
    }

    private BasePage getPage(String pageName) {
        HashSet<Object> pagesSet = new HashSet<>();
        Class<?> clazz = AllPages.class;
        Field[] pages = clazz.getDeclaredFields();
        BasePage result = null;
        for (Field page : pages) {
            page.setAccessible(true);
            String annotationValue = page.getAnnotation(Page.class).value();
            pagesSet.add(annotationValue);
            try {
                if (annotationValue.equals(pageName)) {
                    result = (BasePage) page.get(clazz.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assertNotNull(format("Описание страницы [%s] отсутствует в классе [%s]", pageName, AllPages.class.getName()), result);
        assertEquals(format("В классе [%s] присутствуют аннотации дубликаты", AllPages.class.getName()), pagesSet.size(), pages.length);
        return result;
    }

    private void pageShouldBeLoaded() {
        assertTrue(format("На странице [%s] отсутствует описание идентификатора загрузки", this.currentPageName), this.currentPage.containsKey("isLoaded"));
        Object isLoadedElement = getWebObject("isLoaded");
        if (isLoadedElement instanceof SelenideElement) {
            ((SelenideElement) isLoadedElement).shouldBe(visible);
        } else {
            ((ElementsCollection) isLoadedElement).first().shouldBe(visible);
        }
    }
}
