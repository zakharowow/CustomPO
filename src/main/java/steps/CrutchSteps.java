package steps;

import io.cucumber.java.bg.И;

import static com.codeborne.selenide.Selenide.sleep;

public class CrutchSteps {
    @И("^приостановка выполнения на \"(.+)\" секунд$")
    public void pauseExec(long seconds) {
        sleep(seconds * 1000);
    }
}
