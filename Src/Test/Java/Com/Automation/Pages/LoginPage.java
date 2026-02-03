package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object Model for Login Page
 */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateToUrl(com.automation.utils.ConfigReader.getAppUrl());
        logger.info("Navigated to login page");
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        enterText(usernameField, username);
        logger.info("Entered username: {}", username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        enterText(passwordField, password);
        logger.info("Entered password");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElement(loginButton);
        logger.info("Clicked on login button");
    }

    /**
     * Perform login
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Performed login with username: {}", username);
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        return getTextFromElement(errorMessage);
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Check if login logo is displayed
     */
    public boolean isLoginLogoDisplayed() {
        return isElementDisplayed(loginLogo);
    }

    /**
     * Check if on login page
     */
    public boolean isOnLoginPage() {
        return isLoginLogoDisplayed();
    }
}
