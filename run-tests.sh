#!/bin/bash

echo "========================================"
echo "Selenium BDD Test Framework"
echo "========================================"
echo ""

show_menu() {
    echo "Select an option:"
    echo "1. Run All Regression Tests"
    echo "2. Run Smoke Tests Only"
    echo "3. Install Dependencies"
    echo "4. Clean and Run Tests"
    echo "5. Generate Reports Only"
    echo "6. Exit"
    echo ""
}

run_regression() {
    echo ""
    echo "Running Regression Tests..."
    mvn clean test
    show_results
}

run_smoke() {
    echo ""
    echo "Running Smoke Tests..."
    mvn clean test -DsuiteXmlFile=testng-smoke.xml
    show_results
}

install_dependencies() {
    echo ""
    echo "Installing Dependencies..."
    mvn clean install -DskipTests
    echo ""
    echo "Dependencies installed successfully!"
    read -p "Press Enter to continue..."
}

clean_and_run() {
    echo ""
    echo "Cleaning and Running Tests..."
    mvn clean install
    show_results
}

open_report() {
    echo ""
    echo "Opening Test Report..."
    if [[ "$OSTYPE" == "darwin"* ]]; then
        open target/extent-reports/ExtentReport.html
    elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
        xdg-open target/extent-reports/ExtentReport.html
    fi
    read -p "Press Enter to continue..."
}

show_results() {
    echo ""
    echo "========================================"
    echo "Test Execution Completed!"
    echo "========================================"
    echo ""
    echo "Reports generated at:"
    echo "- Extent Report: target/extent-reports/ExtentReport.html"
    echo "- Cucumber Report: target/cucumber-reports/cucumber.html"
    echo "- Logs: target/logs/automation.log"
    echo ""
    read -p "Do you want to open the report? (y/n): " openreport
    if [[ "$openreport" == "y" ]] || [[ "$openreport" == "Y" ]]; then
        if [[ "$OSTYPE" == "darwin"* ]]; then
            open target/extent-reports/ExtentReport.html
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            xdg-open target/extent-reports/ExtentReport.html
        fi
    fi
    echo ""
    read -p "Press Enter to continue..."
}

while true; do
    clear
    echo "========================================"
    echo "Selenium BDD Test Framework"
    echo "========================================"
    echo ""
    show_menu
    read -p "Enter your choice (1-6): " choice
    
    case $choice in
        1) run_regression ;;
        2) run_smoke ;;
        3) install_dependencies ;;
        4) clean_and_run ;;
        5) open_report ;;
        6) 
            echo ""
            echo "Thank you for using Selenium BDD Framework!"
            exit 0
            ;;
        *) 
            echo "Invalid choice! Please try again."
            sleep 2
            ;;
    esac
done
