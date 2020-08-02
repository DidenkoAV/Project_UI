import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Test_authorization {
    @Test(priority = 6,description = "Авторизация по невалидным данным")
    @Severity(SeverityLevel.CRITICAL)
    public void InvalidAuthorization() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        String AccertUrl = "https://www.facebook.com/login/device-based/regular/login/?login_attempt=1&lwv=110";
        driver.get("https://www.facebook.com/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("uitest@inbox.ru");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("j@%Dn,$gMNp2W");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='u_0_b']")).click();
        Thread.sleep(2000);
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertEquals(driver.getCurrentUrl(),AccertUrl);
        Allure.addAttachment("Console log: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER)));
        Thread.sleep(2000);
    }


    @Test(priority = 7,description = "Авторизация по валидным данным")
    @Severity(SeverityLevel.CRITICAL)
    public void successAuthorization() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        String AccertUrl = "https://www.facebook.com/pavel.petrenko.1029";
        driver.get("https://www.facebook.com/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("uitest@inbox.ru");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("j@%Dn,$gMNp2W!");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='u_0_b']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@class='l9j0dhe7 stjgntxs ni8dbmo4']")).click();
        Thread.sleep(2000);
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertEquals(driver.getCurrentUrl(),AccertUrl);
        Allure.addAttachment("Console log: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER)));
        Allure.addAttachment("My attachment", "image/png");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@class='hu5pjgll lzf7d6o1 sp_9v2_9kG0o8E sx_bd622f']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@class='hu5pjgll m6k467ps sp_TYNNVYzk0HY sx_05b641']")).click();

        driver.close();
    }
}