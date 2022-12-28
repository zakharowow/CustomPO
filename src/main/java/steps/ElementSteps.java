package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.bg.И;

import static base.ScenarioDataStore.getCurrentPage;
import static com.codeborne.selenide.Condition.visible;

public class ElementSteps {

    @И("^производится нажатие на элемент \"(.+)\"$")
    public void clickElement(String elementName) {
        getCurrentPage().getElement(elementName).click();
    }

    @И("^проверяется видимость элемента \"(.+)\"$")
    public void checkElement(String elementName) {
        getCurrentPage().getElement(elementName).shouldBe(visible);
    }

    @И("^поле \"(.+)\" заполняется значением \"(.+)\"$")
    public void fillField(String elementName, String value) {
        getCurrentPage().getElement(elementName).setValue(value);
    }

    @И("^производится нажатие на модальный элемент \"(.+)\"$")
    public void closeModal(String elementName) {
        SelenideElement element = getCurrentPage().getElement(elementName);
        if (element.isDisplayed()) {
            element.click();
        }
    }

}
