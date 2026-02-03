# Quick Start Guide

## Get Started in 5 Minutes! ⚡

### Step 1: Prerequisites Check
```bash
# Check Java installation (Required: JDK 11+)
java -version

# Check Maven installation (Required: Maven 3.6+)
mvn -version
```

If not installed, download from:
- Java: https://www.oracle.com/java/technologies/downloads/
- Maven: https://maven.apache.org/download.cgi

### Step 2: Download Dependencies
```bash
cd selenium-bdd-framework
mvn clean install -DskipTests
```

This will download all required libraries (~100MB). Takes 2-3 minutes.

### Step 3: Run Your First Test
```bash
# Run all tests
mvn clean test

# OR run just smoke tests (faster)
mvn clean test -DsuiteXmlFile=testng-smoke.xml
```

### Step 4: View Reports
After tests complete, open:
```
target/extent-reports/ExtentReport.html
```

## 🎯 What Just Happened?

1. Maven downloaded Selenium, Cucumber, and other dependencies
2. Framework executed test scenarios from `.feature` files
3. Tests ran on Chrome browser (or configured browser)
4. Generated reports with screenshots and logs

## 🔧 Common First-Time Issues

### Issue: Browser doesn't open
**Solution**: Framework uses WebDriverManager - it should handle drivers automatically. If not:
```bash
# Clear and reinstall
mvn clean install -U
```

### Issue: Tests fail immediately
**Check**: 
1. Is Chrome installed? (Or change browser in `config.properties`)
2. Is the test website accessible? (Check `app.url` in config)

### Issue: "Module not found" errors in IDE
**Solution**: 
1. In IntelliJ: Right-click project → Maven → Reload Project
2. In Eclipse: Right-click project → Maven → Update Project

## 🎨 Customize Your Tests

### Change Browser
Edit: `src/test/resources/config/config.properties`
```properties
browser=firefox  # Options: chrome, firefox, edge
```

### Run Headless (No browser window)
```properties
headless=true
```

### Change Test URL
```properties
app.url=https://your-website.com
```

## 📝 Next Steps

1. **Read the full README.md** for detailed documentation
2. **Explore feature files** in `src/test/resources/features/`
3. **Check Page Objects** in `src/test/java/com/automation/pages/`
4. **Write your first test** - Follow the "Writing New Tests" section in README

## 🆘 Need Help?

1. Check logs: `target/logs/automation.log`
2. Review reports: `target/extent-reports/ExtentReport.html`
3. See full documentation in `README.md`

---
**You're all set! Happy Testing! 🚀**
