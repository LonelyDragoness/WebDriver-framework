package webdriver_final_task.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import webdriver_final_task.driver.DriverSingleton;
import webdriver_final_task.model.Platform;
import webdriver_final_task.page.GoogleCloudHomePage;
import webdriver_final_task.service.PlatformCreator;
import webdriver_final_task.util.TestListener;

@Listeners({TestListener.class})
public class GoogleCloudCalculatorEmailValidationTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
    }

    @Test(description = "Calculator page validation through email test")
    public void ValidateCalculatorThroughEmailTest() {

        Platform testPlatform = PlatformCreator.withDataFromProperty();

        String emailEstimatedCost = new GoogleCloudHomePage(driver)
               .openPage()
               .openPricingCalculator()
               .estimatePrice(testPlatform)
               .openEmailPage(driver)
               .sendResultsToEmail()
               .getResultsFromEmail(driver);

        Assert.assertEquals(emailEstimatedCost,GoogleCloudHomePage.calculatorEstimatedCost,
               "Calculator page and email page prices doesn't match");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        DriverSingleton.closeDriver();
    }
}
