package base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.HashMap;

import static base.BasePage.getCurrentPage;

public class BaseWebElement {

    public static SelenideElement getField(String fieldName) {
        return (SelenideElement) getElement(fieldName);
    }

    public static ElementsCollection getCollection(String collectionName) {
        return (ElementsCollection) getElement(collectionName);
    }
    private static Object getElement(String elementName) {
        String pageName = getCurrentPage().keySet().iterator().next();
        Object pageElements = getCurrentPage().get(pageName);
        Object element = ((HashMap<?, ?>) pageElements).get(elementName);
        Assert.assertNotNull(String.format("На странице [%s] отсутствует описание элемента [%s]", pageName, elementName), element);
        return element;
    }
}
