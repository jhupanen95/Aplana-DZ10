package ru.rencredit.steps;

import cucumber.api.java.en.When;
import ru.rencredit.pages.MainPage;

public class MainSteps {

    @When("выбран пункт \"(.*)\" главного меню")
    public void selectMenuItem(String menuItem) {
        new MainPage().selectMenuItem(menuItem);
    }

}
