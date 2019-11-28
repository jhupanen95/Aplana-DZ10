package ru.rencredit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ContributionsPage extends BasePage {

    @FindBy(xpath = "//span[@class='calculator__currency-field-text']")
    public List<WebElement> currencyMenu;

    @FindBy(xpath = "//input[@name='amount']")
    public WebElement amount;

    @FindBy(xpath = "//input[@name='replenish']")
    public WebElement replenish;

    @FindBy(xpath = "//select[@name='period']")
    public WebElement period;


    @FindBy(xpath = "//input[@name='capitalization']/..")
    public WebElement CheckBoxCapitalization;

    @FindBy(xpath = "//input[@name='partial_out']/..")
    public WebElement CheckBoxPartialOut;


    @FindBy(xpath = "//span[@class='js-calc-rate']")
    public WebElement rate;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    public WebElement resultAfterPeriod;

    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    public WebElement replenishOnPeriod;

    @FindBy(xpath = "//span[@class='js-calc-earned']")
    public WebElement earned;

    public void selectCurrency(String name) {
        for (WebElement menuItem: currencyMenu) {
            if (menuItem.getText().equalsIgnoreCase(name)) {
                wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
                return;
            }
        }
    }

    public void selectPeriod(String name) {
        Select select = new Select(period);
        select.selectByVisibleText(name);
    }

}
