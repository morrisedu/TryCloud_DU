package com.trycloud.steps;

import com.trycloud.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.trycloud.utility.ConfigReader.confRead;
import static com.trycloud.utility.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDef {
    LoginPage login = new LoginPage();

    @Given("user navigates to the login page")
    public void userGoesToLogInPage() {

    }

    @When("user provides valid credentials")
    public void userLogsInWithValidCredentials() {
        login.login(confRead("username4"), confRead("password"));
    }

    @When("user provides the correct {string} and the correct {string}")
    public void userProvidesTheCorrectAndTheCorrect(String username, String password) {
        login.login(username, password);
    }

    @When("user provides wrong {string} and the wrong {string}")
    public void userProvidesWrongAndTheWrong(String username, String password) {
        login.login(username, password);
    }

    @Then("user should receive the message {string}")
    public void userShouldReceiveTheMessage(String msg) {
        try {
            assertTrue(login.isLoginErrorMessageDisplayed());
            assertEquals(msg, login.getErrorMessage());
        } catch (Exception ex) {
            assertTrue(login.isThrottledErrorMessageDisplayed());
        }
    }

    @Then("user should still be at the login page")
    public void userShouldStillBeAtTheLoginPage() {
        assertEquals(getDriver().getCurrentUrl(), "http://qa3.trycloud.net/index.php/login");
    }

    @Then("required field exception should be displayed")
    public void requiredFieldExceptionShouldBeDisplayed() {
        try {
            assertEquals("Please fill out this field", login.inputValidation("username"));
        } catch (Exception ex) {
            assertEquals("Please fill out this field", login.inputValidation("password"));
        }
    }

    @Then("required field exception, {string}, should be displayed")
    public void requiredFieldExceptionShouldBeDisplayed(String req) {
        try {
            assertEquals(req, login.inputValidation("username"));
        } catch (Exception ex) {
            assertEquals(req, login.inputValidation("password"));
        }
    }
}
