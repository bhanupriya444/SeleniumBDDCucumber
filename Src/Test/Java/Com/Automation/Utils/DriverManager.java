package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

/**
 * Manager class for WebDriver initialization and configuration
 */
public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser configuration
     */
    public static void initializeDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        boolean headless = ConfigReader.isHeadless();

        logger.info("Initializing {} driver in {} mode", browser, headless ? "headless" : "normal");

        try {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless");
                    }
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) {
                        edgeOptions.addArguments("--headless");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            configureDriver();
            logger.info("Driver initialized successfully");

        } catch (Exception e) {
            logger.error("Failed to initialize driver", e);
            throw new RuntimeException("Driver initialization failed: " + e.getMessage());
        }
    }

    /**
     * Configure driver timeouts and window settings
     */
    private static void configureDriver() {
        WebDriver webDriver = driver.get();
        webDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        webDriver.manage().window().maximize();
    }

    /**
     * Get current WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit and clean up driver
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                logger.info("Quitting driver");
                driver.get().quit();
                driver.remove();
            } catch (Exception e) {
                logger.error("Error while quitting driver", e);
            }
        }
    }
  }
