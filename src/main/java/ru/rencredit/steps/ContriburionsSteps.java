package ru.rencredit.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.rencredit.pages.ContributionsPage;
import ru.rencredit.utils.DriverManager;

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
        ContributionsPage page = new ContributionsPage();
        String errorMessage = "Поле \"" + field + "\", Ожидаемое значение \"" + value + "\", Полученное значение \"";
        switch (field) {
            case "Ставка":
                page.wait.until(ExpectedConditions.textToBePresentInElement(page.rate, value));
                Assert.assertTrue(errorMessage + page.getField(page.rate) + "\"", page.getField(page.rate).equals(value));
                break;
            case "К снятию":
                page.wait.until(ExpectedConditions.textToBePresentInElement(page.resultAfterPeriod, value));
                Assert.assertTrue(errorMessage + page.getField(page.resultAfterPeriod) + "\"", page.getField(page.resultAfterPeriod).equals(value));
                break;
            case "Начисленно":
                page.wait.until(ExpectedConditions.textToBePresentInElement(page.earned, value));
                Assert.assertTrue(errorMessage + page.getField(page.earned) + "\"", page.getField(page.earned).equals(value));
                break;
            case "Пополнение":
                page.wait.until(ExpectedConditions.textToBePresentInElement(page.replenishOnPeriod, value));
                Assert.assertTrue(errorMessage + page.getField(page.replenishOnPeriod) + "\"", page.getField(page.replenishOnPeriod).equals(value));
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
                    fillFieldStep(k, v);
                }
        );
    }

    @When("выбранны чекбоксы:")
    public void checkBoxesStep(Map<String,String> fields) {
        fields.forEach(
                (k, v) -> {
                    checkBoxClickerStep(k, v);
                }
        );
    }

    @Then("проверка результатов:")
    public void checkfillFieldsStep(Map<String,String> fields) {
        fields.forEach(
                (k, v) -> {
                    checkFieldStep(k, v);
                }
        );
    }

}
