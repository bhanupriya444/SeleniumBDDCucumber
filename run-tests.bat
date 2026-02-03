@echo off
echo ========================================
echo Selenium BDD Test Framework
echo ========================================
echo.

:menu
echo Select an option:
echo 1. Run All Regression Tests
echo 2. Run Smoke Tests Only
echo 3. Install Dependencies
echo 4. Clean and Run Tests
echo 5. Generate Reports Only
echo 6. Exit
echo.
set /p choice="Enter your choice (1-6): "

if "%choice%"=="1" goto regression
if "%choice%"=="2" goto smoke
if "%choice%"=="3" goto install
if "%choice%"=="4" goto clean
if "%choice%"=="5" goto report
if "%choice%"=="6" goto end

echo Invalid choice! Please try again.
echo.
goto menu

:regression
echo.
echo Running Regression Tests...
call mvn clean test
goto results

:smoke
echo.
echo Running Smoke Tests...
call mvn clean test -DsuiteXmlFile=testng-smoke.xml
goto results

:install
echo.
echo Installing Dependencies...
call mvn clean install -DskipTests
echo.
echo Dependencies installed successfully!
pause
goto menu

:clean
echo.
echo Cleaning and Running Tests...
call mvn clean install
goto results

:report
echo.
echo Opening Test Report...
start target\extent-reports\ExtentReport.html
pause
goto menu

:results
echo.
echo ========================================
echo Test Execution Completed!
echo ========================================
echo.
echo Reports generated at:
echo - Extent Report: target\extent-reports\ExtentReport.html
echo - Cucumber Report: target\cucumber-reports\cucumber.html
echo - Logs: target\logs\automation.log
echo.
set /p openreport="Do you want to open the report? (y/n): "
if /i "%openreport%"=="y" start target\extent-reports\ExtentReport.html
echo.
pause
goto menu

:end
echo.
echo Thank you for using Selenium BDD Framework!
exit
