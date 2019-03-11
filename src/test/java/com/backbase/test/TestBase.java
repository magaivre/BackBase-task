package com.backbase.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.backbase.datareaders.JsonClass;
import com.backbase.utilities.CaptureScreenShotHandler;
import com.backbase.utilities.Log;

/**
 * @author Maged.Mamdouh
 * Initialise the driver with corresponding OS with parameters sent from command line or testng xml file
 */
public abstract class TestBase {

    public WebDriver driver;

    public static JsonClass jsonTestData;

    public static String BaseURl;

    String userDirectory;

    static Logger logger;

    public enum BROWSERS {
        CHROME("Chrome"), FIREFOX("Firefox"), SAFARI("Safari");

        private final String browserName;

        private BROWSERS(String browserName) {
            this.browserName = browserName;
        }

        public String getBrowserName() {
            return browserName;
        }
    }

    public enum OS {
        MAC("Mac OS"), WIN("Windows");

        private final String osName;

        private OS(final String osName) {
            this.osName = osName;
        }

        public String getOsName() {
            return osName;
        }

    }

    /**
     * Initialize the test data and log file
     * @throws FileNotFoundException
     * @throws IOException
     */
    @BeforeSuite
    public void initiateData() throws FileNotFoundException, IOException {
        jsonTestData = new JsonClass();
        userDirectory = System.getProperty("user.dir");
        String log4jConfigFile = userDirectory + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator
                + "log4j.properties";
        ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jConfigFile));
        Configurator.initialize(null, source);
        logger = Logger.getLogger(getClass());
    }

    /**
     * Detect the OS and Open the corresponding browser using parameter sent in testng xml file
     * @param WindowBrowser
     * @param URL
     */
    @BeforeClass
    @Parameters({ "browser", "url" })
    public void startDriver(@Optional("chrome") String WindowBrowser, @Optional("http://computer-database.herokuapp.com/computers") String URL) {

        final String os = System.getProperty("os.name");
        userDirectory = System.getProperty("user.dir");
        Log.info("Starting to intialise driver");
        Log.info("OS environment: " + os);
        Log.info("Browser: " + WindowBrowser);
        if (WindowBrowser.equalsIgnoreCase(BROWSERS.FIREFOX.getBrowserName())) {
            final StringBuilder geckoDriverPath = new StringBuilder();
            geckoDriverPath.append(userDirectory + File.separator + "src" + File.separator + "test" + File.separator + "resources");
            if (os.contains(OS.MAC.getOsName())) {
                geckoDriverPath.append(File.separator + "mac" + File.separator + "geckodriver");
            } else if (os.contains(OS.WIN.getOsName())) {
                geckoDriverPath.append(File.separator + "win" + File.separator + "geckodriver.exe");
            }
            System.setProperty("webdriver.gecko.driver", geckoDriverPath.toString());
            driver = new FirefoxDriver();
        } else if (WindowBrowser.equalsIgnoreCase(BROWSERS.CHROME.getBrowserName())) {
            final StringBuilder chromeDriverPath = new StringBuilder();
            System.out.println(userDirectory);
            chromeDriverPath.append(userDirectory + File.separator + "src" + File.separator + "test" + File.separator + "resources");
            if (os.contains(OS.MAC.getOsName())) {
                chromeDriverPath.append(File.separator + "mac" + File.separator + "chromedriver");
            } else if (os.contains(OS.WIN.getOsName())) {
                chromeDriverPath.append(File.separator + "win" + File.separator + "chromedriver.exe");
            }
            System.setProperty("webdriver.chrome.driver", chromeDriverPath.toString());
            driver = new ChromeDriver();
        } else if (WindowBrowser.equalsIgnoreCase(BROWSERS.SAFARI.getBrowserName())) {
            driver = new SafariDriver();
        }
        driver.manage().window().setSize(new Dimension(1440, 844));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        BaseURl = URL;
        Log.info("Driver initialised successfully");
        driver.get(BaseURl);
        Log.info("Opening URl: " + URL);
    }

    /**
     * Closing the driver
     */
    @AfterClass(alwaysRun = true)
    public void stopDriver() {
        Log.info("Closing driver");
        driver.quit();
    }

    /**
     * @param result
     * Taking screenshot when test failure
     */
    @AfterMethod
    public void screeshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Log.info("Capturting screenshot");
            CaptureScreenShotHandler.captureScreenshot(driver, result.getName());
        }
    }
}
