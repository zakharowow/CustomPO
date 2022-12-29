package base;

import io.cucumber.java.Scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static base.DuplicateChecker.checkPagesAndElementsDuplicates;

public class ScenarioDataStore {
    private static final HashMap<Thread, HashSet<Object>> scenarioDataStore = new HashMap<>();

    public void initScenarioData(Scenario scenario) {
        checkPagesAndElementsDuplicates();
        HashSet<Object> dataList = new HashSet<>();
        dataList.add(scenario);
        scenarioDataStore.put(Thread.currentThread(), dataList);
    }

    public static void setCurrentPage(String pageName) {
        cleanCurrentPage();
        scenarioDataStore.get(Thread.currentThread()).add(new CurrentPage(pageName));
    }

    private static void cleanCurrentPage() {
        HashSet<Object> elements = new HashSet<>(scenarioDataStore.get(Thread.currentThread()));
        for (Object obj : elements) {
            if (obj instanceof CurrentPage) {
                scenarioDataStore.get(Thread.currentThread()).remove(obj);
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
