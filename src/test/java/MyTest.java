import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTest {

    private ChromeDriver driver;
    private WebDriverWait wait;

    @Before
    public void startTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/denzi/OneDrive/Рабочий стол/chromedriver.exe/");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www2.hm.com/ru_ru/index.html");

    }

    @Test
    public void FirstTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/nav/ul[2]/li[4]/a")));
        WebElement element = driver.findElement(By.xpath("/html/body/header/nav/ul[2]/li[4]/a"));
        element.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu-links']/li[4]/ul/li[3]/a")));
        WebElement catalog = driver.findElement(By.xpath("//*[@id='menu-links']/li[4]/ul/li[3]/a"));
        catalog.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='page-content']/div/div[3]/div[1]/div[1]/form/fieldset[6]/button")));
        WebElement product = driver.findElement(By.xpath("//*[@id='page-content']/div/div[3]/div[1]/div[1]/form/fieldset[6]/button"));
        product.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='side-drawer-1']/div/div/div[1]/div/ul/li[1]")));
        WebElement sort = driver.findElement(By.xpath("//*[@id='side-drawer-1']/div/div/div[1]/div/ul/li[1]"));
        sort.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='drawer-sort']/ul/li[3]")));
        WebElement sort1 = driver.findElement(By.xpath("//*[@id='drawer-sort']/ul/li[3]"));
        sort1.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='side-drawer-1']/div/div/div[2]/button[2]")));
        WebElement sort2 = driver.findElement(By.xpath("//*[@id='side-drawer-1']/div/div/div[2]/button[2]"));
        sort2.click();

        Thread.sleep(5000);
        List<WebElement> lstElement = driver.findElements(By.xpath("//*[@id='page-content']/div/div[3]/ul/li/article/div[2]/strong/span"));
        ArrayList<Double> lstPrice = new ArrayList<Double>();
        boolean flag = true;
        for (var item : lstElement) {
            String temp = item.getText();
            temp = temp.replace("&nbsp;", " ");
            String[] vs = temp.split(" ");
            lstPrice.add(Double.parseDouble(vs[0] + vs[1]));
        }
        for (int i = 1; i < lstPrice.size(); i++) {
            if (lstPrice.get(i) < lstPrice.get(i - 1))
            {
                flag = false;
            }
        }
        Assert.assertTrue(flag);

    }

    @Test
    public void SecondTest()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-content']/div/div[3]/section[5]/ul/li/a")));
        WebElement element = driver.findElement(By.xpath("//*[@id='main-content']/div/div[3]/section[5]/ul/li/a"));
        String actualTooltip = element.getAttribute("title");
        System.out.println(actualTooltip);
        driver.quit();
    }

    @Test
    public void ThirdTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/nav/ul[1]/li[1]/div/a[1]")));
        WebElement element = driver.findElement(By.xpath("/html/body/header/nav/ul[1]/li[1]/div/a[1]"));
        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[11]/div/div/button")));
        WebElement element1 = driver.findElement(By.xpath("/html/body/div[11]/div/div/button"));
        element1.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[12]/div/div/div/form/button")));
        driver.findElement(By.xpath("//*[@id=\"modal-signin-email\"]")).sendKeys("Ploho@hm.ru");
        driver.findElement(By.xpath("//*[@id=\"modal-signin-password\"]")).sendKeys("Privet123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[12]/div/div/div/form/button")));
        WebElement element2 = driver.findElement(By.xpath("/html/body/div[12]/div/div/div/form/button"));
        element2.click();
        System.out.println(driver.findElement(By.xpath("//*[@id='modal-signin-dob-required-error']")).getText());
    }
    @After
    public void endTest()
    {
        driver.quit();
    }
}
