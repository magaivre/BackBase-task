package com.backbase.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.backbase.businesshandlers.HomePageBusinessHandler;
import com.backbase.utilities.Log;

/**
 * @author Maged.Mamdouh
 * Contains the test cases for home page
 */
public class HomeTest extends TestBase {

    HomePageBusinessHandler homePageBussinessHandler;

    /**
     * Initialize the data will be used in the tests
     */
    @BeforeClass
    public void init() {
        homePageBussinessHandler = new HomePageBusinessHandler(driver);
        Log.info("Test data intialised successfully");
    }

    /**
     * Check all the page elements are displayed successfully
     */
    @Test
    public void homePageRendering() {

        Log.info("Starting test case: homePageRendering");
        assertTrue(homePageBussinessHandler.pageHeaderDisplayed(), "Page header not displayed");
        assertTrue(homePageBussinessHandler.pageTitleDisplayed(), "Page title not displayed");
        assertTrue(homePageBussinessHandler.addNewComputerDisplayed(), "Add new computer button not displayed");
        assertTrue(homePageBussinessHandler.searchBoxDisplayed(), "Search box not displayed");
        assertTrue(homePageBussinessHandler.searchButtonDisplayed(), "Search button not displayed");
        try {
            assertTrue(homePageBussinessHandler.computersTableDisplayed(), "No Computers data displayed");
        } catch (Exception e) {
            assertTrue(homePageBussinessHandler.noComputersDisplayed(), "Computers  displayed");
        }
        assertTrue(homePageBussinessHandler.paginationDisplayed(), "Pagination not displayed");
        Log.info("Test case homePageRendering passed successfully");

    }

    /**
     * Check search is working as expected
     */
    @Test
    public void searchWithExistingName() {
        String result = homePageBussinessHandler.searchWithWord("ACE");
        assertTrue(result.contains("ACE"));
    }

    /**
     * Check the result when search with non exist word
     */
    @Test
    public void searchWithNonExistingName() {
        String result = homePageBussinessHandler.searchWithNonExistWord("!!");
        assertTrue(result.contains("Nothing"));
    }

    /**
     * Check pagination are working as expected
     */
    @Test
    public void checkPagination() {
        String result = homePageBussinessHandler.checkPagination();
        assertEquals(result, "1","Pagination not working as expected");
    }

}
