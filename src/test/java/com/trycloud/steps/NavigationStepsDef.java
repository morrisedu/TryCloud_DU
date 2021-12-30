package com.trycloud.steps;

import com.trycloud.pages.Navigation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;

import static com.trycloud.pages.Navigation.linkIsDisplayed;
import static com.trycloud.utility.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.*;

public class NavigationStepsDef {
    @And("user clicks on {string}")
    public void userClicksOn(String str) {
        Navigation.goToLink(str);
    }

    @Then("user should be on the {string} page")
    public void userShouldBeOnThePage(String nav) {
        String expected_page = nav + " - Trycloud QA";
        System.out.println(expected_page);
        assertEquals(expected_page, getDriver().getTitle());
    }

    @Then("the following links should be displayed")
    public void shouldBeDisplayed(List<String> navs) {
        for (String nav : navs) {
            assertTrue(linkIsDisplayed(nav));
        }
    }
}
