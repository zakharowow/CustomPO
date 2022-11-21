package helpers;

import java.util.HashMap;
import java.util.Map;

public class VariableStorage {
    private static final Map<String,String> storage = new HashMap<>();

    public static void setVariable(String variableName, String variableValue){
        storage.put(variableName, variableValue);
    }

    public static String getVariable(String variableName){
        return storage.get(variableName);
    }


}
