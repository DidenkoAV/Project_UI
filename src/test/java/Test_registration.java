import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test_registration {
    public boolean searchElement(By locatorKey) {
        WebDriver driver = DriverBase.getDriver();
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    @Test( priority = 1,description = "Проверка триггера при регистрации без указания имени")
    @Severity(SeverityLevel.CRITICAL)
    public void a_emptyName() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbb _5634']")));
        Allure.addAttachment("Console log: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER)));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='firstname']")).sendKeys("Ivan");
    }
    @Test(priority = 2,description = "Проверка триггера при регистрации без указания фамилии")
    @Severity(SeverityLevel.CRITICAL)
    public void b_emptySurname() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        Thread.sleep(2000);
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbb _5634']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='lastname']")).sendKeys("Ivanov");
    }

    @Test(priority = 3,description = "Проверка триггера на корректность email")
    @Severity(SeverityLevel.CRITICAL)
    public void c_reEnterEmail() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        driver.findElement(By.xpath("//*[@name='reg_email__']")).sendKeys("test@mail.ru");
        Thread.sleep(2000);
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertTrue(searchElement(By.xpath("//*[@name='reg_email_confirmation__']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='reg_email_confirmation__']")).sendKeys("test@mail.r");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='reg_email__']")).click();
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbb _5634']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='reg_email_confirmation__']")).sendKeys("u");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).sendKeys("1");
        Thread.sleep(2000);
    }

    @Test(priority = 4,description = "Проверка триггера при регистрации без указания пола и невалидной даты рождения")
    @Severity(SeverityLevel.CRITICAL)
    public void d_emptyGen() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        Thread.sleep(2000);
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbc _8esb img sp_nK4QBEPgZVv sx_5b4a54']")));
        Allure.addAttachment("Console log: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER)));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='u_0_7']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Assert.assertTrue(searchElement(By.xpath("//*[@name='birthday_age']")));
        driver.findElement(By.xpath("//*[@name='birthday_age']")).sendKeys("18");
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@class='_42ft _4jy0 layerConfirm _2rsa uiOverlayButton _4jy3 _4jy1 selected _51sy']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        Thread.sleep(7000);
    }
    @Test(priority = 5,description = "Проверка триггера при регистрации на некорректный пароль")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка триггера при регистрации на некорректный пароль")
    public void e_checkIncorrectPass() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        DriverBase.makeScreen(driver,"image/png/Screen.png");
        Path content = Paths.get("image/png/Screen.png");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Screen", is);
        }
        Thread.sleep(3000);
        Assert.assertTrue(searchElement(By.xpath("//*[@id='reg_error_inner']")));
    }
}
