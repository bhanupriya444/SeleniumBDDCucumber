package com.automation.stepdefinitions;

import com.automation.pages.CartPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step Definitions for Products Feature
 */
public class ProductsStepDefinitions {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    public ProductsStepDefinitions() {
        this.loginPage = new LoginPage();
        this.productsPage = new ProductsPage();
        this.cartPage = new CartPage();
    }

    @Given("user is logged in with username {string} and password {string}")
    public void userIsLoggedInWithUsernameAndPassword(String username, String password) {
        loginPage.navigateToLoginPage();
        loginPage.login(username, password);
        Assert.assertTrue(productsPage.isOnProductsPage(), 
                "Login failed - user not on products page");
    }

    @When("user is on products page")
    public void userIsOnProductsPage() {
        Assert.assertTrue(productsPage.isOnProductsPage(), 
                "User is not on products page");
    }

    @Then("user should see list of products")
    public void userShouldSeeListOfProducts() {
        Assert.assertTrue(productsPage.areProductsDisplayed(), 
                "Products are not displayed");
        Assert.assertTrue(productsPage.getProductCount() > 0, 
                "No products found on the page");
    }

    @And("products should have name and price")
    public void productsShouldHaveNameAndPrice() {
        Assert.assertFalse(productsPage.getAllProductNames().isEmpty(), 
                "Product names are not displayed");
        Assert.assertFalse(productsPage.getAllProductPrices().isEmpty(), 
                "Product prices are not displayed");
    }

    @And("user adds {string} to cart")
    public void userAddsToCart(String productName) {
        productsPage.addProductToCart(productName);
    }

    @Then("cart badge should show {int} item")
    public void cartBadgeShouldShowItem(int expectedCount) {
        Assert.assertTrue(productsPage.isCartBadgeDisplayed(), 
                "Cart badge is not displayed");
        String actualCount = productsPage.getCartBadgeCount();
        Assert.assertEquals(actualCount, String.valueOf(expectedCount), 
                "Cart badge count does not match");
    }

    @Then("cart badge should show {int} items")
    public void cartBadgeShouldShowItems(int expectedCount) {
        cartBadgeShouldShowItem(expectedCount);
    }

    @And("user navigates to cart page")
    public void userNavigatesToCartPage() {
        productsPage.clickCartIcon();
        Assert.assertTrue(cartPage.isOnCartPage(), 
                "User is not on cart page");
    }

    @And("user should see {string} in cart")
    public void userShouldSeeInCart(String productName) {
        Assert.assertTrue(cartPage.isProductInCart(productName), 
                "Product not found in cart: " + productName);
    }

    @And("user sorts products by {string}")
    public void userSortsProductsBy(String sortOption) {
        productsPage.selectSortOption(sortOption);
    }

    @Then("products should be sorted by price in ascending order")
    public void productsShouldBeSortedByPriceInAscendingOrder() {
        // Wait a moment for sorting to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(productsPage.areProductsSortedByPriceAscending(), 
                "Products are not sorted by price in ascending order");
    }
}
