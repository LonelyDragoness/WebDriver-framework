package webdriver_final_task.util;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webdriver_final_task.model.Platform;

import java.util.HashMap;

public class WebElementGenerator {

    @FindBy(xpath = "//md-option[@id='select_option_206']")
    static WebElement seriesOptionNOne;

    @FindBy(xpath = "//md-option[@id='select_option_402']")
    static WebElement machineTypeStandardEight;

    @FindBy(id = "select_option_441")
    static WebElement numberOfGpusOptionOne;

    @FindBy(id = "select_option_448")
    static WebElement gpuTypeVHundredOption;

    @FindBy(id = "select_option_423")
    static WebElement ssdAmountTwoWithTwoHundredSeventyFive;

    @FindBy(id = "select_option_223")
    static WebElement datacenterLocationFrankfurt;

    @FindBy(id = "select_option_104")
    static WebElement committedUsageOneYear;

    public WebElementGenerator(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    static HashMap<String, String> webElementsToEstimate = new HashMap<>();

    public static HashMap<String, String> generateWebElements(Platform platform) {
        switch (platform.getSeriesOption()) {
            case "n1":
                webElementsToEstimate.put("seriesOption", "//md-option[@id='select_option_206']");
                break;
            case "n2":
                webElementsToEstimate.put("seriesOption", "//md-option[@id='select_option_203']");
                break;
        }
        switch (platform.getMachineTypeOption()) {
            case "n1-standard-8    (vCPUs: 8, RAM: 30 GB)":
                webElementsToEstimate.put("machineType", "//md-option[@id='select_option_402']");
                break;
            case "n1-standard-16    (vCPUs: 16, RAM: 60 GB)":
                webElementsToEstimate.put("machineType", "//md-option[@id='select_option_399']");
                break;
        }
        switch (platform.getNumberOfGpus()) {
            case "1 GPU":
                webElementsToEstimate.put("numberOfGpusOption", "select_option_441");
                break;
            case "4 GPUs":
                webElementsToEstimate.put("numberOfGpusOption", "select_option_439");
                break;
        }
        switch (platform.getGpuType()) {
            case "NVIDIA Tesla V100":
                webElementsToEstimate.put("gpuType", "select_option_448");
                break;
            case "NVIDIA Tesla T4":
                webElementsToEstimate.put("gpuType", "select_option_445");
                break;
        }
        switch (platform.getSsdAmount()) {
            case "2x375 Gb":
                webElementsToEstimate.put("ssdAmount", "select_option_423");
                break;
            case "16x375 Gb":
                webElementsToEstimate.put("ssdAmount", "select_option_426");
                break;
        }
        switch (platform.getDatacenterLocation()) {
            case "Frankfurt (europe-west3)":
                webElementsToEstimate.put("datacenterLocation", "select_option_223");
                break;
            case "Iowa (us-central1)":
                webElementsToEstimate.put("datacenterLocation", "select_option_210");
                break;
        }
        switch (platform.getCommittedUsage()) {
            case "1 Year":
                webElementsToEstimate.put("committedUsage", "select_option_104");
                break;
            case "3 Years":
                webElementsToEstimate.put("committedUsage", "select_option_103");
                break;
        }
        return webElementsToEstimate;
    }
}
