package steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.bg.И;

import static base.ScenarioDataStore.getCurrentPage;
import static com.codeborne.selenide.CollectionCondition.size;

public class CollectionSteps {

    @И("^в коллекции \"(.+)\" присутствует \"(.+)\" элементов$")
    public void fillField(String collectionName, int quantity) {
        getCurrentPage().getCollection(collectionName).shouldHave(size(quantity));
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу номер \"(.+)\"$")
    public void clickElementCollection(String collectionName, int number) {
        getCurrentPage().getCollection(collectionName).get(number + 1).click();
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу с текстом \"(.+)\"$")
    public void clickElementWithText(String collectionName, String text) {
        getCurrentPage().getCollection(collectionName).findBy(Condition.text(text)).click();
    }

}
