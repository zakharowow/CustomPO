package steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.bg.И;

import static com.codeborne.selenide.CollectionCondition.size;
import static elements.BaseClass.getCollection;

public class CollectionSteps {

    @И("^в коллекции \"(.+)\" присутствует \"(.+)\" элементов$")
    public void fillField(String collectionName, int quantity) {
        getCollection(collectionName).shouldHave(size(quantity));
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу номер \"(.+)\"$")
    public void clickElementCollection(String collectionName, int number) {
        getCollection(collectionName).get(number+1).click();
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу с текстом \"(.+)\"$")
    public void clickElementWithText(String collectionName, String text) {
        getCollection(collectionName).findBy(Condition.text(text)).click();
    }

}
