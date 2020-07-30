import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Test_authorization {
    @AfterMethod
    public void hooksAfter() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    @Test
    public void successAuthorization() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        String AccertUrl = "https://www.facebook.com/pavel.petrenko.1029";
        driver.get("https://www.facebook.com/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("uitest@inbox.ru");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("j@%Dn,$gMNp2W!");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='u_0_b']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='l9j0dhe7 stjgntxs ni8dbmo4']")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),AccertUrl);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='hu5pjgll lzf7d6o1 sp_9v2_9kG0o8E sx_bd622f']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='hu5pjgll m6k467ps sp_TYNNVYzk0HY sx_05b641']")).click();
    }
    @Test
    //@Severity(SeverityLevel.CRITICAL)
   // @Description("Авторизация по невалидным данным")
    public void InvalidAuthorization() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        String AccertUrl = "https://www.facebook.com/login/device-based/regular/login/?login_attempt=1&lwv=110";
        driver.get("https://www.facebook.com/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("uitest@inbox.ru");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("j@%Dn,$gMNp2W");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='u_0_b']")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),AccertUrl);
    }
}
