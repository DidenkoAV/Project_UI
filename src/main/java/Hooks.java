import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Hooks {



    @AfterMethod
    public void hooksAfterMeth() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

}
