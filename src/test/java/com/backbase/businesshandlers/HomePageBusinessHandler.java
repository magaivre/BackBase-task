package com.backbase.businesshandlers;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.backbase.pageobjects.HomePage;

public class HomePageBusinessHandler {

    HomePage homePage;

    /**
     * Initialize the page 
     * @param driver
     */
    public HomePageBusinessHandler(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    /**
     * Return true when page header is displayed
     * @return
     */
    public boolean pageHeaderDisplayed() {
        return homePage.pageHeaderDisplayed();
    }

    /**
     * Return true when page title is displayed
     * @return
     */
    public boolean pageTitleDisplayed() {
        return homePage.pageTitleDisplayed();
    }

    /**
     * Return true when add new computer button is displayed
     * @return
     */
    public boolean addNewComputerDisplayed() {
        return homePage.addButtonDisplayed();
    }

    /**
     * Return true when search text box is appeared
     * @return
     */
    public boolean searchBoxDisplayed() {
        return homePage.searchBoxDisplayed();
    }

    /**
     * Return true when search button is displayed
     * @return
     */
    public boolean searchButtonDisplayed() {
        return homePage.searchButtonDisplayed();
    }

    /**
     * Return true when table that contains the computer is displayed
     * @return
     */
    public boolean computersTableDisplayed() {
        return homePage.computersTableDisplayed();
    }

    /**
     * Return true when no computer message is displayed
     * @return
     */
    public boolean noComputersDisplayed() {
        return homePage.noComputersDisplayed();
    }

    /**
     * Return true when there is a pagination
     * @return
     */
    public boolean paginationDisplayed() {
        return homePage.paginationDisplayed();
    }

    /**
     * Search and return the computer name from the results
     * @param name
     * @return
     */
    public String searchWithWord(String name) {
        return homePage.setFilterText(name)
               .clickSearchSubmitButton()
               .getComputerName();
    }

    /**
     * Search with non exist word and return the message
     * @param name
     * @return
     */
    public String searchWithNonExistWord(String name) {
        return homePage.setFilterText(name)
                .clickSearchSubmitButton()
                .getNoComputerMessage();
    }
    
    /**
     * Clicking on next and previous button and retrieve the text in the pagination
     * @return
     */
    public String checkPagination(){
        return homePage.clickNextButton()
                .clickPreviousButton()
                .getCurrentDisplaying().substring(11, 12);
    }
    
    /**
     * Open the details for the first computer in the table
     */
    public void openFirstComputer() {
        homePage.clickComputerInTable();
    }

    /**
     * Get the computer details in the table and convert the dates
     * @return
     */
    public List<String> getComputerDetailsTable() {
        List<String> computerDetailsTable = homePage.getComputerDetails();
        String introducedDate = computerDetailsTable.get(1);
        String discontinuedDate = computerDetailsTable.get(2);
        if (introducedDate != null) {
            computerDetailsTable.remove(1);
            computerDetailsTable.add(1, formateDate(introducedDate));
        }
        if (discontinuedDate != null) {
            computerDetailsTable.remove(2);
            computerDetailsTable.add(2, formateDate(discontinuedDate));
        }
        return computerDetailsTable;
    }

    /**
     * Convert the month from text to number
     * @param date
     * @return
     */
    public String formateDate(String date) {
        String[] dateDivided= date.split(" ");
        switch (dateDivided[1]) {
            case "Jan":
                date = dateDivided[2] + "-01-" + dateDivided[0];
                break;
            case "Feb":
                date = dateDivided[2] + "-02-" + dateDivided[0];
                break;
            case "Mar":
                date = dateDivided[2] + "-03-" + dateDivided[0];
                break;
            case "Apr":
                date = dateDivided[2] + "-04-" + dateDivided[0];
                break;
            case "May":
                date = dateDivided[2] + "-05-" + dateDivided[0];
                break;
            case "Jun":
                date = dateDivided[2] + "-06-" + dateDivided[0];
                break;
            case "Jul":
                date = dateDivided[2] + "-07-" + dateDivided[0];
                break;
            case "Aug":
                date = dateDivided[2] + "-08-" + dateDivided[0];
                break;
            case "Sep":
                date = dateDivided[2] + "-09-" + dateDivided[0];
                break;
            case "Oct":
                date = dateDivided[2] + "-10-" + dateDivided[0];
                break;
            case "Nov":
                date = dateDivided[2] + "-11-" + dateDivided[0];
                break;
            case "Dec":
                date = dateDivided[2] + "-12-" + dateDivided[0];
                break;

        }

        return date;

    }

}
