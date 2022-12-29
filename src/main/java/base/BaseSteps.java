package base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static base.ScenarioDataStore.getCurrentPage;

public class BaseSteps {

    public ElementsCollection getCollection(String collectionName){
        return getCurrentPage().getElementsCollection(collectionName);
    }

    public SelenideElement getElement(String elementName){
        return getCurrentPage().getSelenideElement(elementName);
    }



}
