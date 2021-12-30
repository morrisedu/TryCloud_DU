package com.trycloud.steps;

import com.trycloud.pages.Navigation;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.regex.Pattern;

import static com.trycloud.utility.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModulesAccessStepDef {
    @Then("user should have access to the following modules")
    public void userShouldHaveAccessToTheFollowingModules(List<String> modules) {
        String baseURL = "http://qa3.trycloud.net/index.php/apps/";
        String fullURL = "";

        for (String module : modules) {
            Navigation.goToLink(module);

            fullURL = "^" + baseURL + module + "/$[.*]/";

            assertTrue(Pattern.matches("^http://qa3\\.trycloud\\.net/index\\.php/apps/" + module + "/.*", getDriver().getCurrentUrl()));
        }
    }

    @Then("user should have access to the following {string}")
    public void userShouldHaveAccessToTheFollowing(String module) {
        Navigation.goToLink(module);

        assertTrue(Pattern.matches("^http://qa3\\.trycloud\\.net/index\\.php/apps/" + module + "/.*", getDriver().getCurrentUrl())); // ToDo cgeck this Regular Expression

    }
}
