package steps;

import base.BaseSteps;
import com.codeborne.selenide.Condition;
import io.cucumber.java.bg.И;
import org.openqa.selenium.By;

import static base.ScenarioDataStore.getCurrentPage;
import static com.codeborne.selenide.CollectionCondition.size;

public class CollectionSteps extends BaseSteps {

    @И("^в коллекции \"(.+)\" присутствует \"(.+)\" элементов$")
    public void fillField(String collectionName, int quantity) {
        getCollection(collectionName).shouldHave(size(quantity));
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу номер \"(.+)\"$")
    public void clickElementCollection(String collectionName, int number) {
        getCollection(collectionName).get(number + 1).click();
    }

    @И("^в коллекции \"(.+)\" выбирается элемент номер \"(.+)\" и производится клик по фрагменту \"(.+)\"$")
    public void clickCollectionPart(String collectionName, int elementNumber, String partName) {
        getCollection(collectionName).get(elementNumber - 1).find(By.xpath(getPath(partName))).click();
    }

    @И("^в коллекции \"(.+)\" производится клик по элементу с текстом \"(.+)\"$")
    public void clickElementWithText(String collectionName, String text) {
        getCollection(collectionName).findBy(Condition.text(text)).click();
    }

}
