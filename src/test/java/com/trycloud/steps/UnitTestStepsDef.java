package com.trycloud.steps;

import io.cucumber.java.en.Then;

import static com.trycloud.utility.BrowserUtil.waitFor;

public class UnitTestStepsDef {
    @Then("wait for {int} seconds then close")
    public void waitForSecondsThenClose(int secs) {
        waitFor(secs);
    }
}
