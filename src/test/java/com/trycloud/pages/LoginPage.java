package com.trycloud.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.trycloud.utility.ConfigReader.confRead;
import static com.trycloud.utility.Driver.getDriver;

public class LoginPage {
    @FindBy(id = "user")
    private WebElement username_input;

    @FindBy(id = "password")
    private WebElement password_input;

    @FindBy(id = "submit-form")
    private WebElement login_btn;

    @FindBy(xpath = "//p[contains(@class, 'warning wrongPasswordMsg')]")
    private WebElement loginErrorMessage;

    /**
     * Displayed when too many failed login attempts have been detected
     */
    @FindBy(xpath = "//p[contains(@class, 'warning throttledMsg')]")
    private WebElement throttledErrorMessage;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public static void goTo() {
        getDriver().get(confRead("trycloud_url"));
    }

    public  void login(String username, String password) {
        // Open URL
        goTo();

        // Login
        username_input.sendKeys(username);
        password_input.sendKeys(password);
        login_btn.click();
    }

    /**
     * Method to login with different usernames & passwords
     */
    public void login() {
        int rand = new Faker().number().numberBetween(1, 4);

        String username = "";

        switch (rand) {
            case 1:
                username = confRead("username1");
                break;
            case 2:
                username = confRead("username2");
                break;
            case 3:
                username = confRead("username3");
                break;
            case 4:
                username = confRead("username4");
                break;
        }

        username_input.sendKeys(username);
        password_input.sendKeys(confRead("password"));
        login_btn.click();
    }

    /**
     * Method to check if login error message is displayed
     * @return boolean, true if it is displayed, otherwise, false
     */
    public boolean isLoginErrorMessageDisplayed(){
        return loginErrorMessage.isDisplayed();
    }

    /**
     * Method to check if throttled error message is displayed
     * @return boolean, true if it is displayed, otherwise, false
     */
    public boolean isThrottledErrorMessageDisplayed() {
        return throttledErrorMessage.isDisplayed();
    }

    /**
     * Method to get the error message displayed on wrong credentials displayed
     * @return stripped error message
     */
    public String getErrorMessage() {
        return loginErrorMessage.getText().strip();
    }

    /**
     * Method to get the throttled error message displayed once too many failed login attempts have been detected
     * @return stripped error message
     */
    public String getThrottledMessage() {
        return throttledErrorMessage.getText().strip();
    }

    public String inputValidation(String input) {
        switch (input) {
            case "username":
                return username_input.getAttribute("validationMessage");
            case "password":
                return password_input.getAttribute("validationMessage");
        }

        return null;
    }
}
