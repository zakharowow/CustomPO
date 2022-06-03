package steps;

import elements.BaseClass;
import io.cucumber.java.bg.И;

import static com.codeborne.selenide.CollectionCondition.size;

public class CollectionSteps {

    @И("^в коллекции \"(.+)\" присутствует \"(.+)\" элементов$")
    public void fillField(String collectionName, int quantity) {
        BaseClass.getCollection(collectionName).shouldHave(size(quantity));
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу \"(.+)\"$")
    public void clickElementCollection(String collectionName, int number) {
        BaseClass.getCollection(collectionName).get(number+1).click();
    }

}
