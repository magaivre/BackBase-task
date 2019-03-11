package com.backbase.businesshandlers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.backbase.pageobjects.ComputerPage;
import com.backbase.pageobjects.HomePage;

/**
 * @author Maged.Mamdouh
 * This class contains the business and scenarios in computer page
 */
public class ComputerPageBusinessHandler {

    HomePage homePage;

    ComputerPage computerPage;

    /**
     * @param driver
     * intialise the pages with the driver
     */
    public ComputerPageBusinessHandler(WebDriver driver) {

        homePage = new HomePage(driver);
        computerPage = new ComputerPage(driver);
    }

    /**
     * @param name
     * @param introducedDate
     * @param discontinuedDate
     * @param company
     * Adding new computer and fill the form with parameters
     * 
     */
    public void addNewComputer(String name, String introducedDate, String discontinuedDate, String company) {

         homePage.clickAddNewComputer()
                .setComputerName(name)
                .setIntroduceDate(introducedDate)
                .setDiscontunedDate(discontinuedDate)
                .selectCompanyName(company)
                .clickSubmitButton();
    }
    
    /**
     * @param computerSearch
     * @param name
     * @param introducedDate
     * @param discontinuedDate
     * @param company
     * Search for exist computer and edit the data with parameters
     */
    public void editComputer(String computerSearch, String name, String introducedDate, String discontinuedDate, String company) {

         homePage.setFilterText(computerSearch)
                .clickSearchSubmitButton()
                .clickComputerInTable()
                .setComputerName(name)
                .setIntroduceDate(introducedDate)
                .setDiscontunedDate(discontinuedDate)
                .selectCompanyName(company)
                .clickSubmitButton();
    }
    
    /**
     * @param computerSearch
     * Delete computer for the given parameter
     */
    public void deleteComputer(String computerSearch) {
        
         homePage.setFilterText(computerSearch)
                .clickSearchSubmitButton()
                .clickComputerInTable()
                .clickDeleteButton();
        
    }
    
    /**
     * @param computerSearch
     * @param name
     * @param introducedDate
     * @param discontinuedDate
     * @param company
     * @return
     * Search and edit computer parameters then cancel the edit form
     */
    public String editAndCancel(String computerSearch, String name, String introducedDate, String discontinuedDate, String company) {
 
        return homePage.setFilterText(computerSearch)
                .clickSearchSubmitButton()
                .clickComputerInTable()
                .setComputerName(name)
                .setIntroduceDate(introducedDate)
                .setDiscontunedDate(discontinuedDate)
                .selectCompanyName(company)
                .clickCancelButton()
                .getComputerName();
    }
    
    /**
     * @param name
     * @param introducedDate
     * @param discontinuedDate
     * @param company
     * Add new computer with wrong date format
     */
    public void addwithWrongData(String name, String introducedDate, String discontinuedDate, String company) {

        homePage.clickAddNewComputer()
        .setComputerName(name)
        .setIntroduceDate(introducedDate)
        .setDiscontunedDate(discontinuedDate)
        .selectCompanyName(company)
        .clickSubmitButton();

    }
    
    
    /**
     * Navigate to home page
     */
    public void navigateToHomePage() {
        homePage.clickPageHeader();
    }
    
    /**
     * @return
     * Get the success message after adding ,updating or deleting computer
     */
    public String getSuccessMessage() {
        return homePage.getsuccessMessage();
    }
    
    /**
     * @return
     * Get title for computer page
     */
    public String getPageTitle() {
        return computerPage.getPageTitle();
    }
    
    /**
     * @return
     * Get computer details inside the computer page
     */
    public List<String> getComputerDetailsForm(){
        List<String> computerDetailsForm = new ArrayList<>();
        computerDetailsForm.add(computerPage.getComputerName());
        computerDetailsForm.add(computerPage.getIntroducedDate());
        computerDetailsForm.add(computerPage.getDiscontinuedDate());
        computerDetailsForm.add(computerPage.getCompanyName());
        return computerDetailsForm;
    }
}
