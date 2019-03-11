package com.backbase.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Maged.Mamdouh
 * Helper class that contains methods can be used in all the page objects
 */
public abstract class BasePage {

    protected WebDriver driver;

    public int timeInMillis = 50;

    public int timeInSecs = 10;

    Actions action;

    final WebDriverWait wait;
    
    Select select;

    BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        action = new Actions(driver);
        wait = new WebDriverWait(driver, timeInSecs, timeInMillis);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void setText(WebElement element, String text) {
        element.sendKeys(text);
    }


    public void waitForVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    
    public void selectByVisibleText(WebElement element , String text){
        select = new Select(element);
        select.selectByVisibleText(text);
    }
    
    public String getTextFromSelect(WebElement element){
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

}
