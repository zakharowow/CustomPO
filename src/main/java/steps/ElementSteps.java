package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.bg.И;

import static com.codeborne.selenide.Condition.visible;
import static elements.BaseClass.getField;

public class ElementSteps {

    @И("^производится нажатие на элемент \"(.+)\"$")
    public void clickElement(String elementName) {
        getField(elementName).click();
    }

    @И("^проверяется видимость элемента \"(.+)\"$")
    public void checkElement(String elementName) {
        getField(elementName).shouldBe(visible);
    }

    @И("^поле \"(.+)\" заполняется значением \"(.+)\"$")
    public void fillField(String elementName, String value) {
        getField(elementName).setValue(value);
    }

    @И("^производится нажатие на модальный элемент \"(.+)\"$")
    public void closeModal(String elementName) {
        SelenideElement element = getField(elementName);
        if (element.isDisplayed()) {
            element.click();
        }
    }

}
