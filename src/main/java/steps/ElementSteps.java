package steps;

import com.codeborne.selenide.SelenideElement;
import elements.BaseClass;
import io.cucumber.java.bg.И;

import static com.codeborne.selenide.Condition.visible;

public class ElementSteps {

    @И("^производится нажатие на элемент \"(.+)\"$")
    public void clickElement(String elementName) {
        BaseClass.getField(elementName).click();
    }

    @И("^проверяется видимость элемента \"(.+)\"$")
    public void checkElement(String elementName) {
        BaseClass.getField(elementName).shouldBe(visible);
    }

    @И("^поле \"(.+)\" заполняется значением \"(.+)\"$")
    public void fillField(String elementName, String value) {
        BaseClass.getField(elementName).setValue(value);
    }

    @И("^производится нажатие на модальный элемент \"(.+)\"$")
    public void closeModal(String elementName) {
        SelenideElement element = BaseClass.getField(elementName);
        if (element.isDisplayed()) {
            element.click();
        }
    }

}
