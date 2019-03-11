package com.backbase.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.backbase.businesshandlers.ComputerPageBusinessHandler;
import com.backbase.businesshandlers.HomePageBusinessHandler;
import com.backbase.utilities.Log;

/**
 * @author Maged.Mamdouh
 * Contains testcases for computer page test
 */
public class ComputerTest extends TestBase {

    ComputerPageBusinessHandler computerPageBusinessHandler;

    HomePageBusinessHandler homePageBusinessHandler;

    String computerName, introducedDate, discontinuedDate, companyName, editComputerName, editIntroducedDate, editDiscontinuedDate, editCompanyName,
            worngComputerName, wrongIntroducedDate, wrongDiscontinuedDate, wrongCompanyName;

    /**
     * Initialize the test data that will be used in the test cases
     * //TODO Make the test data for computer using generator
     */
    @BeforeClass
    public void init() {
        computerPageBusinessHandler = new ComputerPageBusinessHandler(driver);
        homePageBusinessHandler = new HomePageBusinessHandler(driver);
        computerName = jsonTestData.getData("Create").get("computerName");
        introducedDate = jsonTestData.getData("Create").get("introducedDate");
        discontinuedDate = jsonTestData.getData("Create").get("discontinuedDate");
        companyName = jsonTestData.getData("Create").get("companyName");
        editComputerName = jsonTestData.getData("Edit").get("editComputerName");
        editIntroducedDate = jsonTestData.getData("Edit").get("editIntroducedDate");
        editDiscontinuedDate = jsonTestData.getData("Edit").get("editDiscontinuedDate");
        editCompanyName = jsonTestData.getData("Edit").get("editCompanyName");
        worngComputerName = jsonTestData.getData("WrongData").get("worngComputerName");
        wrongIntroducedDate = jsonTestData.getData("WrongData").get("wrongIntroducedDate");
        wrongDiscontinuedDate = jsonTestData.getData("WrongData").get("wrongDiscontinuedDate");
        wrongCompanyName = jsonTestData.getData("WrongData").get("wrongCompanyName");
        Log.info("Test data intialised successfully");
    }

    /**
     * Add new computer test case with test data
     */
    @Test(priority = 1)
    public void addNewComputerTest() {

        Log.info("Starting test case: addNewComputer");
        // Create new computer
        computerPageBusinessHandler.addNewComputer(computerName, introducedDate, discontinuedDate, companyName);
        assertTrue(computerPageBusinessHandler.getSuccessMessage().contains(computerName), "Computer has not been added");
        Log.info("Test case addNewComputer passed successfully");
    }

    /**
     * Add new computer with wrong data
     */
    @Test(priority = 2)
    public void addComputerWithWrongData() {
        Log.info("Starting test case: addComputerWithWrongData");
        // Create new computer with wrong data
        computerPageBusinessHandler.addwithWrongData(worngComputerName, wrongIntroducedDate, wrongDiscontinuedDate, wrongCompanyName);
        String pageTitle = computerPageBusinessHandler.getPageTitle();
        computerPageBusinessHandler.navigateToHomePage();
        assertTrue(pageTitle.contains("Add a computer"), "Computer has been added");
        Log.info("Test case addComputerWithWrongData passed successfully");
    }

    /**
     * Create a new computer with name already exists
     */
    @Test(priority = 3, dependsOnMethods = { "addNewComputerTest" })
    public void addDuplicateComputer() {
        Log.info("Starting test case: addDuplicateComputer");
        computerPageBusinessHandler.addNewComputer(computerName, introducedDate, discontinuedDate, companyName);
        String pageTitle = computerPageBusinessHandler.getPageTitle();
        computerPageBusinessHandler.navigateToHomePage();
        assertTrue(pageTitle.contains("Add a computer"), "Computer has been added");
        Log.info("Test case addDuplicateComputer passed successfully");
    }

    /**
     * Open computer details and edit the data and click cancel
     */
    @Test(priority = 4, dependsOnMethods = { "addNewComputerTest" })
    public void editWithoutSave() {
        Log.info("Starting test case: editComputerWithoutSave");
        String name = computerPageBusinessHandler.editAndCancel(computerName, editComputerName, editIntroducedDate, editDiscontinuedDate, editCompanyName);
        assertEquals(name, computerName, "Computer has been edited");
        Log.info("Test case editComputerWithoutSave passed successfully");
    }

    /**
     * Open added computer and edit data and save
     */
    @Test(priority = 5, dependsOnMethods = { "addNewComputerTest" })
    public void editExistComputerTest() {
        Log.info("Starting test case: editExistComputer");
        computerPageBusinessHandler.editComputer(computerName, editComputerName, editIntroducedDate, editDiscontinuedDate, editCompanyName);
        assertTrue(computerPageBusinessHandler.getSuccessMessage().contains(editComputerName), "Computer has not been edited");
        Log.info("Test case editExistComputer passed successfully");
    }

    /**
     * Delete added computer
     */
    @Test(priority = 6, dependsOnMethods = { "editExistComputerTest" })
    public void deleteComputer() {
        Log.info("Starting test case: deleteComputer");
        computerPageBusinessHandler.deleteComputer(editComputerName);
        assertTrue(computerPageBusinessHandler.getSuccessMessage().contains("deleted"), "Computer has not been deleted");
        Log.info("Test case deleteComputer passed successfully");
    }

    /**
     * Check the computer data in the table and in the details form are the same
     */
    @Test(priority = 7)
    public void checkComputerDetails() {
        Log.info("Starting test case: checkComputerDetails");
        List<String> computerDetailsTable = homePageBusinessHandler.getComputerDetailsTable();
        homePageBusinessHandler.openFirstComputer();
        List<String> computerDetailsForm = computerPageBusinessHandler.getComputerDetailsForm();
        assertEquals(computerDetailsTable, computerDetailsForm, "Computer details are not the same");
        Log.info("Test case checkComputerDetails passed successfully");
    }

}
