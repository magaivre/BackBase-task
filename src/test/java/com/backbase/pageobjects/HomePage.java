package com.backbase.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Maged.Mamdouh
 * Contains the locators and methods that deals with the elements
 */
public class HomePage extends BasePage {

    @FindBy(id = "add")
    WebElement addNewComputer;

    @FindBy(id = "searchsubmit")
    WebElement filterSubmitButton;

    @FindBy(id = "searchbox")
    WebElement filterTextBox;

    @FindBy(xpath = "//*[@id='main']/table/tbody/tr[1]/td[1]/a")
    WebElement computerNameInTable;

    @FindBy(css = "li[class='prev']>a")
    WebElement previousButton;

    @FindBy(css = "li[class='next']>a")
    WebElement nextButton;

    @FindBy(css = "li[class='current']")
    WebElement currentDisplaying;

    @FindBy(id = "pagination")
    WebElement pagination;

    @FindBy(css = "h1[class='fill']>a")
    WebElement header;

    @FindBy(css = "div[class='alert-message warning']")
    WebElement successfulMessage;

    @FindBy(xpath = "//*[@id='main']/h1")
    WebElement pageTitle;

    @FindBy(css = "table[class='computers zebra-striped']")
    WebElement computersTable;

    @FindBy(css = "div[class='well']")
    WebElement noComputers;

    @FindBy(xpath = "//*[@id='main']/table/tbody/tr[1]/td")
    List<WebElement> computerDetails;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ComputerPage clickAddNewComputer() {
        waitForVisibilityOf(addNewComputer);
        click(addNewComputer);
        return new ComputerPage(driver);
    }

    public HomePage setFilterText(String searchText) {
        waitForVisibilityOf(filterTextBox);
        filterTextBox.clear();
        setText(filterTextBox, searchText);
        return this;
    }

    public HomePage clickSearchSubmitButton() {
        click(filterSubmitButton);
        return this;
    }

    public ComputerPage clickComputerInTable() {
        click(computerNameInTable);
        return new ComputerPage(driver);
    }

    public HomePage clickNextButton() {
        waitForVisibilityOf(nextButton);
        click(nextButton);
        return this;
    }

    public HomePage clickPreviousButton() {
        click(previousButton);
        return this;
    }

    public boolean pageHeaderDisplayed() {
        waitForVisibilityOf(header);
        return header.isDisplayed();
    }

    public boolean pageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }

    public boolean addButtonDisplayed() {
        return addNewComputer.isDisplayed();
    }

    public boolean searchBoxDisplayed() {
        return filterTextBox.isDisplayed();
    }

    public boolean searchButtonDisplayed() {
        return filterSubmitButton.isDisplayed();
    }

    public boolean computersTableDisplayed() {
        return computersTable.isDisplayed();
    }

    public boolean noComputersDisplayed() {
        return noComputers.isDisplayed();
    }

    public boolean paginationDisplayed() {
        return pagination.isDisplayed();
    }

    public String getComputerName() {
        waitForVisibilityOf(computerNameInTable);
        return computerNameInTable.getText();
    }

    public String getNoComputerMessage() {
        waitForVisibilityOf(noComputers);
        return noComputers.getText();
    }

    public String getCurrentDisplaying() {
        return currentDisplaying.getText();
    }

    public String getsuccessMessage() {
        waitForVisibilityOf(successfulMessage);
        return successfulMessage.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public HomePage clickPageHeader() {
        waitForVisibilityOf(header);
        click(header);
        return this;
    }

    public List<String> getComputerDetails() {
        List<String> computerDetail = new ArrayList<String>();
        for (int i = 0; i < computerDetails.size(); i++) {
            computerDetail.add(i, computerDetails.get(i).getText());
        }
        return computerDetail;
    }
}
