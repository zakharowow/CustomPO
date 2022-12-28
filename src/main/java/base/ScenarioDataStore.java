package base;

import io.cucumber.java.Scenario;

import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioDataStore {
    private static final HashMap<Thread, ArrayList<Object>> scenarioDataStore = new HashMap<>();

    public void initScenarioData(Scenario scenario) {
        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(scenario);
        scenarioDataStore.put(Thread.currentThread(), dataList);
    }

    public static void setCurrentPage(String pageName) {
        cleanCurrentPage();
        scenarioDataStore.get(Thread.currentThread()).add(new CurrentPage(pageName));
    }

    private static void cleanCurrentPage() {
        ArrayList<Object> elements = new ArrayList<>(scenarioDataStore.get(Thread.currentThread()));
        for (Object obj : elements) {
            if (obj instanceof CurrentPage) {
                scenarioDataStore.get(Thread.currentThread()).remove(elements.indexOf(obj));
            }
        }
    }

    public static CurrentPage getCurrentPage() {
        CurrentPage result = null;
        for (Object obj : scenarioDataStore.get(Thread.currentThread())) {
            if (obj instanceof BasePage) {
                result = (CurrentPage) obj;
                break;
            }
        }
        return result;
    }

}
