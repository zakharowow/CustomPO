package base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.HashMap;

import static base.BasePage.getCurrentPage;
import static java.lang.String.format;

public class BaseWebElement {

    public static SelenideElement getElement(String fieldName) {
        return (SelenideElement) getWebObject(fieldName);
    }

    public static SelenideElement getElement(String fieldName, boolean isLoaded) {
        return (SelenideElement) getWebObject(fieldName, isLoaded);
    }

    public static ElementsCollection getCollection(String collectionName) {
        return (ElementsCollection) getWebObject(collectionName);
    }

    public static ElementsCollection getCollection(String collectionName, boolean isLoaded) {
        return (ElementsCollection) getWebObject(collectionName, isLoaded);
    }

    private static Object getWebObject(String elementName) {
        return getWebObject(elementName, false);
    }

    private static Object getWebObject(String elementName, boolean isLoaded) {
        String pageName = getCurrentPage().keySet().iterator().next();
        Object pageElements = getCurrentPage().get(pageName);
        Object element = ((HashMap<?, ?>) pageElements).get(elementName);
        if (!isLoaded) {
            Assert.assertNotNull(format("На странице [%s] отсутствует описание элемента [%s]", pageName, elementName), element);
        }
        return element;
    }
}
