package steps;

import base.BaseSteps;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.bg.И;

import static base.ScenarioDataStore.getCurrentPage;
import static com.codeborne.selenide.Condition.visible;

public class ElementSteps extends BaseSteps {

    @И("^производится нажатие на элемент \"(.+)\"$")
    public void clickElement(String elementName) {
        getElement(elementName).click();
    }

    @И("^проверяется видимость элемента \"(.+)\"$")
    public void checkElement(String elementName) {
        getElement(elementName).shouldBe(visible);
    }

    @И("^поле \"(.+)\" заполняется значением \"(.+)\"$")
    public void fillField(String elementName, String value) {
        getElement(elementName).setValue(value);
    }

    @И("^производится нажатие на модальный элемент \"(.+)\"$")
    public void closeModal(String elementName) {
        SelenideElement element = getElement(elementName);
        if (element.isDisplayed()) {
            element.click();
        }
    }

}
