package ru.rencredit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.rencredit.utils.DriverManager;

public abstract class BasePage {

    protected WebDriverWait wait  = new WebDriverWait(DriverManager.getDriver(), 30);

    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void fillField(WebElement field, String value){
        wait.until(ExpectedConditions.visibilityOf(field));
        field.click();
        field.clear();
        field.sendKeys(value);
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getField(WebElement element) {
        return element.getText();
    }

}