package com.automation.stepdefinitions;

import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step Definitions for Login Feature
 */
public class LoginStepDefinitions {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    public LoginStepDefinitions() {
        this.loginPage = new LoginPage();
        this.productsPage = new ProductsPage();
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        loginPage.navigateToLoginPage();
        Assert.assertTrue(loginPage.isOnLoginPage(), "User is not on login page");
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("user clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user should be redirected to products page")
    public void userShouldBeRedirectedToProductsPage() {
        Assert.assertTrue(productsPage.isOnProductsPage(), 
                "User is not redirected to products page");
    }

    @And("user should see the products page title")
    public void userShouldSeeTheProductsPageTitle() {
        String pageTitle = productsPage.getPageTitle();
        Assert.assertEquals(pageTitle, "Products", 
                "Products page title does not match");
    }

    @Then("user should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedErrorMessage) {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message is not displayed");
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), 
                "Error message does not match. Expected: " + expectedErrorMessage + 
                ", Actual: " + actualErrorMessage);
    }

    @Then("user should see {string}")
    public void userShouldSee(String result) {
        switch (result.toLowerCase()) {
            case "success":
                Assert.assertTrue(productsPage.isOnProductsPage(), 
                        "Login was not successful");
                break;
            case "locked":
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                        "Error message not displayed for locked user");
                break;
            default:
                Assert.fail("Unknown result type: " + result);
        }
    }
}
