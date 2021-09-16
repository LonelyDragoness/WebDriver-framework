package webdriver_final_task.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
