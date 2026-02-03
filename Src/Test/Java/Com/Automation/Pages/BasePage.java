package com.automation.pages;

import com.automation.utils.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utils.ConfigReader;

import java.time.Duration;

/**
 * Base Page class containing common methods for all page objects
 */
public class BasePage {
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for element to be visible
     */
    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Click on element
     */
    protected void clickElement(WebElement element) {
        waitForElementClickable(element).click();
        logger.info("Clicked on element: {}", element);
    }

    /**
     * Enter text in element
     */
    protected void enterText(WebElement element, String text) {
        waitForElementVisible(element).clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' in element", text);
    }

    /**
     * Get text from element
     */
    protected String getTextFromElement(WebElement element) {
        String text = waitForElementVisible(element).getText();
        logger.info("Retrieved text: {}", text);
        return text;
    }

    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Scroll to element
     */
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Navigate to URL
     */
    public void navigateToUrl(String url) {
        driver.get(url);
        logger.info("Navigated to URL: {}", url);
    }
}
