package com.backbase.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Maged.Mamdouh
 * Contains the locators and methods that deals with the elements
 */
public class ComputerPage extends BasePage {

    @FindBy(css = "input[class='btn danger']")
    WebElement deleteComputer;

    @FindBy(css = "input[class='btn primary']")
    WebElement submitComputer;

    @FindBy(css = "a[class='btn']")
    WebElement cancelComputer;

    @FindBy(id = "name")
    WebElement computerName;

    @FindBy(id = "introduced")
    WebElement introducedDate;

    @FindBy(id = "discontinued")
    WebElement discontinuedDate;

    @FindBy(id = "company")
    WebElement companyName;

    @FindBy(xpath = "//*[@id='main']/h1")
    WebElement pageTitle;

    public ComputerPage(WebDriver driver) {
        super(driver);
    }

    public ComputerPage setComputerName(String name) {
        waitForVisibilityOf(computerName);
        computerName.clear();
        setText(computerName, name);
        return this;
    }

    public ComputerPage setIntroduceDate(String date) {
        introducedDate.clear();
        setText(introducedDate, date);
        return this;
    }

    public ComputerPage setDiscontunedDate(String date) {
        discontinuedDate.clear();
        setText(discontinuedDate, date);
        return this;
    }

    public ComputerPage selectCompanyName(String name) {
        selectByVisibleText(companyName, name);
        return this;
    }

    public HomePage clickSubmitButton() {
        click(submitComputer);
        return new HomePage(driver);
    }

    public HomePage clickDeleteButton() {
        click(deleteComputer);
        return new HomePage(driver);
    }

    public HomePage clickCancelButton() {
        click(cancelComputer);
        return new HomePage(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getComputerName() {
        return computerName.getAttribute("value");
    }

    public String getIntroducedDate() {
        return introducedDate.getAttribute("value");
    }

    public String getDiscontinuedDate() {
        return discontinuedDate.getAttribute("value");
    }

    public String getCompanyName() {
        return getTextFromSelect(companyName);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

}
