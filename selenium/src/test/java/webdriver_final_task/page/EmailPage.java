package webdriver_final_task.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPage {

    private String emailTime;
    boolean emailIsNotArrived = true;
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(id = "login")
    private WebElement emailAddress;

    @FindBy(xpath = "//div[@id='refreshbut']/button")
    private WebElement checkEmailButton;

    @FindBy(xpath = "//button[@aria-label='Cancel']")
    private WebElement cancelButton;

    @FindBy(id = "newmail")
    private WebElement newMailButton;

    @FindBy(id = "ifmail")
    private WebElement mailIframe;

    @FindBy(xpath = "//button[@id='refresh']")
    private WebElement refreshButton;

    @FindBy(xpath = "//div[@id='mail']/descendant::h2[text()]")
    private WebElement emailEstimatedCost;

    @FindBy(id = "ifinbox")
    private WebElement mailListFrame;

    @FindBy(xpath = "//input[@class='mc']")
    private WebElement emailListCheckbox;

    @FindBy(xpath = "//span[@class='ellipsis']")
    private WebElement emailTimeString;

    public EmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openEmailPage(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        ((JavascriptExecutor)driver).executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.get("https://yopmail.com/");

        emailAddress.sendKeys("TiamatLair");
        checkEmailButton.click();

        driver.switchTo().window(tabs.get(0));
        logger.info("Email page was opened");
        return new GoogleCloudHomePage(driver);
    }

    public String getResultsFromEmail(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(newMailButton));
        driver.switchTo().frame(mailIframe);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mail']/descendant::h2[text()]")));

        Pattern timePattern = Pattern.compile("\\d\\d:\\d\\d:\\d\\d");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");

        while (emailIsNotArrived) {
            Matcher timeMatcher = timePattern.matcher(emailTimeString.getText());
            if (timeMatcher.find()) {
                emailTime = timeMatcher.group(0);
            }
            LocalTime emailReceivedTime = formatter.parseDateTime(emailTime).toLocalTime();

            switch (emailReceivedTime.compareTo(GoogleCloudHomePage.emailSendingTime)) {
                case -1:
                    driver.switchTo().defaultContent();
                    refreshButton.click();
                    driver.switchTo().frame(mailListFrame);
                    WebDriverWait waiter = new WebDriverWait(driver, 10);
                    waiter.until(ExpectedConditions.elementToBeClickable(emailListCheckbox));
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(mailIframe);
                    break;
                case 1:
                    emailIsNotArrived = false;
                    break;
            }
        }

        String emailMonthlyCost = emailEstimatedCost.getText().replaceAll("[^0-9,.]","");
        logger.info("Result from email was acquired");
        return "Total Estimated Cost: USD " + emailMonthlyCost + " per 1 month";
    }
}
