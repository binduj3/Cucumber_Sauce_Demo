package steps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before
    public void start() {
        openBrowser();
    }

    @After
    public void end(Scenario scenario) {
       byte[] pic;
       if (scenario.isFailed()) {
            pic = takeScreenshot("Failed/" + scenario.getName());
        } else {
            pic = takeScreenshot("Passed/" + scenario.getName());
        }

        scenario.attach(pic, "image/png", scenario.getName());
        closeBrowser();
    }
}
