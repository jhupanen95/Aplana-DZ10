package ru.rencredit.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import org.junit.Assert;
import ru.rencredit.pages.ContributionsPage;

import java.util.Map;

public class ContriburionsSteps {

    @When("выбрана валюта \"(.*)\"")
    public void selectCurrencyStep(String menuItem) {
        new ContributionsPage().selectCurrency(menuItem);
    }

    @When("выбран на срок \"(.*)\"")
    public void selectPeriodStep(String value) {
        new ContributionsPage().selectPeriod(value);
    }

    @Step("чекбокс \"{0}\" нажат \"{1}\"")
    public void checkBoxClickerStep(String chBox, String bool) {

        ContributionsPage page = new ContributionsPage();

        switch (chBox) {
            case "Ежемесячная капитализация":
                if ("да".equalsIgnoreCase(bool)) page.click(page.CheckBoxCapitalization);
                break;
            case "Частичное снятие":
                if ("да".equalsIgnoreCase(bool)) page.click(page.CheckBoxPartialOut);
                break;
            default:
                Assert.fail("Не найден чекбокс - " + chBox);
        }
    }

    @Step("значение \"{0}\" равно \"{1}\"")
    public void checkFieldStep (String field, String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ContributionsPage page = new ContributionsPage();

        switch (field) {
            case "Ставка":
                Assert.assertTrue(page.getField(page.rate).equals(value));
                break;
            case "К снятию":
                Assert.assertTrue(page.getField(page.resultAfterPeriod).equals(value));
                break;
            case "Начисленно":
                Assert.assertTrue(page.getField(page.earned).equals(value));
                break;
            case "Пополнение":
                Assert.assertTrue(page.getField(page.replenishOnPeriod).equals(value));
                break;
            default:
                Assert.fail("Не найден элемент - " + field);
        }
    }

    @Step("поле \"{0}\" заполняется значением \"{1}\"")
    public void fillFieldStep(String fill, String value) {

        ContributionsPage page = new ContributionsPage();

        switch (fill) {
            case "Сумма вклада":
                page.fillField(page.amount, value);
                break;
            case "Ежемесячное пополнение":
                page.fillField(page.replenish, value);
                break;
            default:
                Assert.fail("Не найдено поле - " + fill);
        }
    }

    @When("заполняются поля:")
    public void fillFieldsStep(Map<String,String> fields) {
        fields.forEach(
                (k, v) -> {
                    try {
                        fillFieldStep(k, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @When("выбранны чекбоксы:")
    public void checkBoxesStep(Map<String,String> fields) {
        fields.forEach(
                (k, v) -> {
                    try {
                        checkBoxClickerStep(k, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Then("проверка результатов:")
    public void checkfillFieldsStep(Map<String,String> fields) {
        fields.forEach(
                (k, v) -> {
                    try {
                        checkFieldStep(k, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
