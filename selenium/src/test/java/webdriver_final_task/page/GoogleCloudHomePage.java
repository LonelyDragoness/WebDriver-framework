package webdriver_final_task.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver_final_task.model.Platform;
import webdriver_final_task.util.WebElementGenerator;

import java.util.HashMap;

public class GoogleCloudHomePage {

    private final WebDriver driver;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String EMAIL = "tiamatlair@yopmail.com";
    public static String calculatorEstimatedCost;
    public static LocalTime emailSendingTime;
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='devsite-searchbox']")
    private WebElement searchButton;

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement searchResultCalculatorLink;

    @FindBy(xpath = "/html/body/section/section/main/devsite-content/article/div[2]/article/devsite-iframe/iframe")
    private WebElement mainIframeElement;

    @FindBy(id = "myFrame")
    private WebElement calculatorIframe;

    @FindBy(id = "input_69")
    private WebElement numberOfInstancesInput;

    @FindBy(id = "select_94")
    private WebElement seriesDropdown;

    @FindBy(id = "select_96")
    private WebElement machineTypeDropdown;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(id = "select_value_label_434")
    private WebElement numberOfGpus;

    @FindBy(id = "select_438")
    private WebElement gpuType;

    @FindBy(id = "select_397")
    private WebElement localSsdDropdown;

    @FindBy(id = "select_99")
    private WebElement datacenterLocationDropdown;

    @FindBy(id = "select_106")
    private WebElement committedUsageDropdown;

    @FindBy(xpath = "//button[@ng-disabled=\"ComputeEngineForm.$invalid || !listingCtrl.machineType['computeServer']\"]")
    private WebElement addToEstimateButton;

    @FindBy (id = "email_quote")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//b[contains(text(), 'Total Estimated Cost:')]")
    private WebElement totalEstimatedCost;

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Google cloud page was opened");
        return this;
    }

    public GoogleCloudHomePage openPricingCalculator() {
        searchButton.click();
        searchInput.sendKeys("Google Cloud Platform Pricing Calculator" + Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(searchResultCalculatorLink));
        searchResultCalculatorLink.click();

        driver.switchTo().frame(mainIframeElement);
        driver.switchTo().frame(calculatorIframe);
        return this;
    }

    private void dropdownElementClicker(WebElement target) {
        WebDriverWait waitForLoading = new WebDriverWait(driver, 10);
        waitForLoading.until(ExpectedConditions.elementToBeClickable(target));
        target.click();
        waitForLoading.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(target)));
    }

    public EmailPage estimatePrice(Platform platform) {

        HashMap<String, String> platformToEstimate = WebElementGenerator.generateWebElements(platform);

        numberOfInstancesInput.sendKeys(platform.getNumberOfInstances());

        seriesDropdown.click();

        dropdownElementClicker(driver.findElement(By.xpath(platformToEstimate.get("seriesOption"))));

        machineTypeDropdown.click();
        dropdownElementClicker(driver.findElement(By.xpath(platformToEstimate.get("machineType"))));

        addGpuCheckbox.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(numberOfGpus));
        numberOfGpus.click();
        dropdownElementClicker(driver.findElement(By.id(platformToEstimate.get("numberOfGpusOption"))));

        gpuType.click();
        dropdownElementClicker(driver.findElement(By.id(platformToEstimate.get("gpuType"))));

        localSsdDropdown.click();
        dropdownElementClicker(driver.findElement(By.id(platformToEstimate.get("ssdAmount"))));

        datacenterLocationDropdown.click();
        dropdownElementClicker(driver.findElement(By.id(platformToEstimate.get("datacenterLocation"))));

        committedUsageDropdown.click();
        dropdownElementClicker(driver.findElement(By.id(platformToEstimate.get("committedUsage"))));

        addToEstimateButton.click();

        WebDriverWait waitForEstimate = new WebDriverWait(driver, 10);
        waitForEstimate.until(ExpectedConditions.elementToBeClickable(emailEstimateButton));
        emailEstimateButton.click();

        calculatorEstimatedCost = totalEstimatedCost.getText();

        System.out.println(driver.getCurrentUrl());
        logger.info("Platform price was estimated");
        return new EmailPage(driver);
    }

    public EmailPage sendResultsToEmail() {
        driver.switchTo().frame(mainIframeElement);
        driver.switchTo().frame(calculatorIframe);

        emailInput.sendKeys(EMAIL);
        sendEmailButton.click();

        emailSendingTime = LocalDateTime.now().toLocalTime();

        logger.info("Result was sent to email");
        return new EmailPage(driver);
    }
}
