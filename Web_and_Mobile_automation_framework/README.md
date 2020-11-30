# MobileApp_Appium_Framework

# Info:

	This framework helps you to run automation scripts in parallel and sequence on multiple Android devices to test an android apk using Java as programming language.

# Prerequisites

	Please make sure to have:

	1. Java, eclipse (any IDE), TestNG plugin.
	2. Android Studio, NPM, Appium, uiautomatorviewer (detailed - https://medium.com/testcult/configuring-appium-in-ubuntu-from-scratch-a9f8edc02d13)
	2. Refer pom.xml file for all dependencies.

# Running the tests

	Once user setup everything in eclipse, then (s)he can run tests using "testng.xml"(applicable based on App) as TestNG Suite. 
	
	For SB app - sugarbox_testng.xml

# Configuration for test data and variables

	1. TestNG.xml file: 
		a) To pass desired capabilities - appPackage, appActivity.
		b) To pass paths of files - .Properties file, excel file (test data) and excel sheet indexing.
		c) To pass device index - on which device you want to run scripts (indexing of excel file).
		d) To pass desired variables (integers or String).

	2. .properties file:
		a) For Global variables across project.

	3. Excel file:
		a) Test data across project.

# Break down into end to end tests
	
	** Prerequisites: 
	a) App should have location permission by default and location should be ON.
	b) User must have a registered Mobile Number (number and Mac combination).
	c) Device Wi-fi should be ON (if it does not allow App to ON auto i.e. Nokia 6.1) 

	There are mainly two classes consist three methods are:

	1. LoginSB-
		-> User will traverse to Login screen and verify necessary elements across screens.
		-> User will enter registered mobile number and navigate to HomePage.

	2. PlayAndDownloadContent-
		-> Method 1: User will play a content and verify it's playing or not.
		-> Method 2: User will start downloading the same content and verify it's downloading or not.
		 

# Built With

	eclipse - IDE
	Selenium web-driver
	Appium driver
	TestNG - parallel or sequence execution of methods
	Maven - jar dependency
	Excel, Properties file - for variables and test data
	Log4j2 - for logging
	ITestListener - for execution of specific method on completion of Test Success, Test Failure
