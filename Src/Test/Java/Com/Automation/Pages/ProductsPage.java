package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object Model for Products Page
 */
public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> productList;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    /**
     * Get page title text
     */
    public String getPageTitle() {
        return getTextFromElement(pageTitle);
    }

    /**
     * Check if on products page
     */
    public boolean isOnProductsPage() {
        return isElementDisplayed(pageTitle) && getPageTitle().equals("Products");
    }

    /**
     * Get number of products displayed
     */
    public int getProductCount() {
        return productList.size();
    }

    /**
     * Check if products are displayed
     */
    public boolean areProductsDisplayed() {
        return !productList.isEmpty();
    }

    /**
     * Add product to cart by name
     */
    public void addProductToCart(String productName) {
        for (WebElement product : productList) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                WebElement addToCartButton = product.findElement(By.tagName("button"));
                clickElement(addToCartButton);
                logger.info("Added product to cart: {}", productName);
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    /**
     * Get cart badge count
     */
    public String getCartBadgeCount() {
        return getTextFromElement(cartBadge);
    }

    /**
     * Check if cart badge is displayed
     */
    public boolean isCartBadgeDisplayed() {
        return isElementDisplayed(cartBadge);
    }

    /**
     * Click on cart icon
     */
    public void clickCartIcon() {
        clickElement(cartIcon);
        logger.info("Clicked on cart icon");
    }

    /**
     * Select sort option
     */
    public void selectSortOption(String option) {
        clickElement(sortDropdown);
        WebElement sortOption = driver.findElement(
                By.xpath("//option[text()='" + option + "']"));
        clickElement(sortOption);
        logger.info("Selected sort option: {}", option);
    }

    /**
     * Get all product names
     */
    public List<String> getAllProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Get all product prices
     */
    public List<Double> getAllProductPrices() {
        return productPrices.stream()
                .map(element -> element.getText().replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    /**
     * Check if products are sorted by price ascending
     */
    public boolean areProductsSortedByPriceAscending() {
        List<Double> prices = getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
