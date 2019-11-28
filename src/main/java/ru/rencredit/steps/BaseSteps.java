package ru.rencredit.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.rencredit.utils.DriverManager;
import ru.rencredit.utils.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    public static Properties properties = TestProperties.getInstance().getProperties();

    @Before
    public static void setUp() {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(properties.getProperty("app.url"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown(Scenario scenario) {
        if(scenario.isFailed()){
            takeScreenshot();
        }
        DriverManager.quitDriver();
    }

    @Attachment(type = "image/png", value = "Скриншот в момент ошибки")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
