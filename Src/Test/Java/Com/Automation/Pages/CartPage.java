package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object Model for Cart Page
 */
public class CartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    /**
     * Get page title
     */
    public String getPageTitle() {
        return getTextFromElement(pageTitle);
    }

    /**
     * Check if on cart page
     */
    public boolean isOnCartPage() {
        return isElementDisplayed(pageTitle) && getPageTitle().equals("Your Cart");
    }

    /**
     * Get cart items count
     */
    public int getCartItemsCount() {
        return cartItems.size();
    }

    /**
     * Check if product is in cart
     */
    public boolean isProductInCart(String productName) {
        return cartItemNames.stream()
                .anyMatch(element -> element.getText().equals(productName));
    }

    /**
     * Get all cart item names
     */
    public List<String> getAllCartItemNames() {
        return cartItemNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Click checkout button
     */
    public void clickCheckoutButton() {
        clickElement(checkoutButton);
        logger.info("Clicked on checkout button");
    }

    /**
     * Click continue shopping button
     */
    public void clickContinueShoppingButton() {
        clickElement(continueShoppingButton);
        logger.info("Clicked on continue shopping button");
    }

    /**
     * Remove product from cart
     */
    public void removeProductFromCart(String productName) {
        for (WebElement item : cartItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                WebElement removeButton = item.findElement(By.tagName("button"));
                clickElement(removeButton);
                logger.info("Removed product from cart: {}", productName);
                return;
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }
}
