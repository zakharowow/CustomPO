package steps;

import helpers.VariableStorage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.bg.И;

import static helpers.VariableStorage.setVariable;

public class VariableSteps {

    @И("^в переменную \"(.+)\" сохраняется значение \"(.+)\"$")
    public void storeVariable(String variableName, String variableValue) {
        setVariable(variableName, variableValue);
    }

    @И("^инициализируются значения переменных:$")
    public void storeDataTableElements(DataTable variableFieldValue) {
        variableFieldValue.asMap(String.class, String.class).forEach(VariableStorage::setVariable);
    }


}
