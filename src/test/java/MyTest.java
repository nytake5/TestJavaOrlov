import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyTest {

    @Test
    public void FirstTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/denzi/OneDrive/Рабочий стол/chromedriver.exe/");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.dns-shop.ru/");

        WebElement element = driver.findElement(By.xpath("//*[@id=\"homepage-desktop-menu-wrap\"]/div/div[1]/div[1]"));
        element.click();

        WebElement catalog = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[3]"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        catalog.click();

        WebElement product = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[1]"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        product.click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        ArrayList<Double> lstPrice = new ArrayList<Double>();
        boolean flag = true;
        int j = 2;
        for (int i = 0; i < 10; i++) {
            try {
                String xPath = "/html/body/div[1]/div/div[2]/div[2]/div[2]/div/div[" + j + "]/div[" + (i + 1) + "]/div[4]/div/div[1]";
                if (xPath.isEmpty()) {
                    j *= 2;
                    i = 0;
                }
                xPath = "/html/body/div[1]/div/div[2]/div[2]/div[2]/div/div[" + j + "]/div[" + (i + 1) + "]/div[4]/div/div[1]";
                String str = driver.findElement(By.xpath(xPath)).getText();
                lstPrice.add(Double.parseDouble(str.split(" ")[0]));
            }
            catch (Exception e)
            {
                break;
            }

            if (i > 1) {
                if (lstPrice.get(i) < lstPrice.get(i - 1))
                {
                    flag = false;
                    break;
                }
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        Assert.assertTrue(flag);
        driver.quit();
    }

    @Test
    public void SecondTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/denzi/OneDrive/Рабочий стол/chromedriver.exe/");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.intel.ru/content/www/ru/ru/homepage.html");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"language-toggle-button\"]"));
        String actualTooltip = element.getAttribute("title");
        System.out.println(actualTooltip);
        driver.quit();
    }

    @Test
    public void ThirdTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/denzi/OneDrive/Рабочий стол/chromedriver.exe/");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.intel.ru/content/www/ru/ru/forms/basic-intel-registration.html");
        wait.until(ExpectedConditions.urlContains("https://www.intel.ru/content/www/ru/ru/forms/basic-intel-registration.html"));
        driver.findElement(By.xpath("//*[@id='FirstName']")).sendKeys("Citilink");
        driver.findElement(By.xpath("//*[@id='LastName']")).sendKeys("Rabotaet");
        driver.findElement(By.xpath("//*[@id='EmailID']")).sendKeys("Ploho@citilink.ru");
        driver.findElement(By.xpath("//*[@id='LoginID']")).sendKeys("DnsKruto");
        driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("NeLuche2$");
        driver.findElement(By.xpath("//*[@id='PasswordConfirmation']")).sendKeys("NeLuche2$");
        driver.findElement(By.xpath("//*[@id='sectionTitle0']/div/div/div/div[5]/div/label/span[2]/select/option[@value='AF']")).click();
        driver.findElement(By.xpath("//*[@id='sectionTitle0']/div/div/div/div[6]/div/label/span[2]/select/option[@value='FIN-ACCT']")).click();
        driver.findElement(By.xpath("//*[@id='sectionTitle0']/div/div/div/div[7]/div[1]/label/span[2]/select/option[5]")).click();
        //можно убирать, для проверки того, что он не пустит тебя дальше без ввода какого-то поля
        driver.findElement(By.xpath("//*[@id='sectionTitle0']/div/div/div/div[7]/div[2]/label")).sendKeys("12345");
        WebElement element = driver.findElement(By.xpath("//*[@id='sectionTitle0']/div/div/div/div[8]/div/div/button"));
        if (element.getAttribute("disabled") != null)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse(false);
        }
    }
}
