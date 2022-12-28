import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"timeline:test-output-thread/"},
        features = "src/main/resources/features",
        glue = "steps",
        tags = "@runnn",
        publish = true,
        monochrome = true,
        stepNotifications = true
)
public class TestRunner {
}
