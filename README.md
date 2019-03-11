# BackBase test task

**Description**
-----
 * Project consists of 4 packages as the following:
     * Businesshandler: This is for handling the business for each page in the website.
     * Datareaders: This contains class that parse the json test data inside the data provider.
     * Pageobjects: This have the locators and methods that deals with the web elements for each page.
     * Test: This contains the test classes for each test scenario than deals with the business handler.
     * Utilities: This contains the classes for logging and capturing the screenshots 
  
 * Every test contains one or more business handler and every business handler deal with one or more page object to make the steps for the test and then after complete the steps verifying the results.
 

**How to run?**
----
There are several ways to run the code after importing the project:
 * Run each test separately by right click on the class and select run as testNG test. 
 * Run the regression-suite.xml as testNG suite you can add the browser and url parameters in it or leave it to take the optional value in testBase class
 * Run through the command line by navigate to the project folder then write <br/>
 `mvn test -Dbrowser=firefox -Durl="http://computer-database.herokuapp.com/computers"` <br/>or mvn clean install for the first run.

The solution include:
* Logging using log4j;
* Taking screenshot on failed tests after each method;
* Report will be in the test-output folder after running the test;
* WebDriver factory;
* Configurator:
     * Run tests in parallel mode through testNG xml file(Please note that there is issue in the TestNG for the parallel execution but it works for the older version) ;
     * Ability to run tests for different browsers/urls by configuring in the xml , commandline or the optional parameter;
     * Ability to run tests for different os by making it generic to get the os and open the corresponding driver.
* Reading test data from file Data.json.

**Libraries/Plugins used**
-------------------
* Selenium: To initiate the driver and deal with the web elements.
* Json: This used to parse the json test data file.
* Log4j: Used for logging.
* Testng: Used for test annotations , asserting on the results and parallel execution. 
* Maven-surefire-plugin: used for configuring the suite xml and parametrised variables through the command line.
* Maven-compiler-plugin: used to compile the project to version 1.7 because it will not work for 1.5 because of multiple catch exceptions.

