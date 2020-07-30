import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverBase {
    private static WebDriver driver;
    static {
        Map<String, Object> disableNotify = new HashMap<String, Object>();
        disableNotify.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("disableNotify", disableNotify);
        System.setProperty("webdriver.chrome.driver", "/Users/didenkoav/IdeaProjects/Project_UI/src/main/resources/chromedriver");
        driver = new ChromeDriver(options);
    }
    static WebDriver getDriver(){
        return driver;
    }
}
