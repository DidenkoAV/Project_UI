import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    @Test
   // @Severity(SeverityLevel.CRITICAL)
   // @Description("Проверка триггера при регистрации без указания имени")
    public void a_emptyName() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbb _5634']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='firstname']")).sendKeys("Ivan");
    }
    @Test
   // @Severity(SeverityLevel.CRITICAL)
   // @Description("Проверка триггера при регистрации без указания фамилии")
    public void b_emptySurname() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        Thread.sleep(1000);
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbb _5634']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='lastname']")).sendKeys("Ivanov");
    }

    @Test
   // @Severity(SeverityLevel.CRITICAL)
   // @Description("Проверка триггера на корректность email")
    public void c_reEnterEmail() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        driver.findElement(By.xpath("//*[@name='reg_email__']")).sendKeys("test@mail.ru");
        Thread.sleep(1000);
        Assert.assertTrue(searchElement(By.xpath("//*[@name='reg_email_confirmation__']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='reg_email_confirmation__']")).sendKeys("test@mail.r");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='reg_email__']")).click();
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbb _5634']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='reg_email_confirmation__']")).sendKeys("u");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).sendKeys("1");
        Thread.sleep(1000);
    }

    @Test
  //  @Severity(SeverityLevel.CRITICAL)
  //  @Description("Проверка триггера при регистрации без указания пола")
    public void d_emptyGen() throws InterruptedException {
        WebDriver driver = DriverBase.getDriver();
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        Thread.sleep(1000);
        Assert.assertTrue(searchElement(By.xpath("//*[@class='_5dbc _8esb img sp_nK4QBEPgZVv sx_5b4a54']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='sex']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        Assert.assertTrue(searchElement(By.xpath("//*[@name='birthday_age']")));
        driver.findElement(By.xpath("//*[@name='birthday_age']")).sendKeys("18");
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='_42ft _4jy0 layerConfirm _2rsa uiOverlayButton _4jy3 _4jy1 selected _51sy']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
    }
    @Test
   // @Severity(SeverityLevel.CRITICAL)
  //  @Description("Проверка триггера при регистрации на некорректный пароль")
    public void e_checkIncorrectPass(){
        WebDriver driver = DriverBase.getDriver();
        Assert.assertTrue(searchElement(By.xpath("//*[@id='reg_error_inner']")));
    }
}
