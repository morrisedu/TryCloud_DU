package com.trycloud.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.trycloud.utility.Driver.closeBrowser;
import static com.trycloud.utility.Driver.getDriver;

/**
 * Hook class contains methods that run before &/or after each scenario
 * With tags, you can run certain code before & after each scenario that is tagged with a particular tag
 */
public class Hooks {
    @Before("@web-ui")
    public void setupDriver() {
        // Setup implicit wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximize window size
        getDriver().manage().window().maximize();
    }

    @After("@web-ui")
    public void tearDown(Scenario scenario) {


        // Taking screenshot if scenario is failed
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        // Close browser
        closeBrowser();
    }
}
