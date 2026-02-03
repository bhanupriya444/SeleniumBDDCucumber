package com.automation.hooks;

import com.automation.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Cucumber Hooks for setup and teardown operations
 */
public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    /**
     * Before hook - runs before each scenario
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("========================================");
        logger.info("Starting scenario: {}", scenario.getName());
        logger.info("========================================");
        DriverManager.initializeDriver();
    }

    /**
     * After hook - runs after each scenario
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario failed: {}", scenario.getName());
                takeScreenshot(scenario);
            } else {
                logger.info("Scenario passed: {}", scenario.getName());
            }
        } catch (Exception e) {
            logger.error("Error in tearDown", e);
        } finally {
            DriverManager.quitDriver();
            logger.info("========================================");
            logger.info("Completed scenario: {}", scenario.getName());
            logger.info("========================================\n");
        }
    }

    /**
     * Take screenshot and attach to report
     */
    private void takeScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            logger.info("Screenshot captured for failed scenario");
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
        }
    }
}
