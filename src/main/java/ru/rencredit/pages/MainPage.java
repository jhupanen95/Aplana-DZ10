package ru.rencredit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@class='service__title-text']")
    public List<WebElement> menu;

    public void selectMenuItem(String name) {
        for (WebElement menuItem: menu) {
            if (menuItem.getText().equalsIgnoreCase(name)) {
                WebElement buttonItem = menuItem.findElement(By.xpath(".//../a[1]"));
                wait.until(ExpectedConditions.elementToBeClickable(buttonItem)).click();
                return;
            }
        }
    }

}
