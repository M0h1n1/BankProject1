//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import java.sql.Driver;
import java.util.Set;

public class Letskode {
    ChromeDriver driver = new ChromeDriver();

    @BeforeTest
    public void setup() {
        driver.get("https://www.letskodeit.com/practice");
        driver.manage().window().maximize();

    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testCheckBoxes() throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='checkbox' and @value='benz']")).click();
        Thread.sleep(5000);
    }

    @Test
    public void testRadioButtons() throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='radio' and @value='honda']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@type='radio' and @value='bmw']")).click();
        Thread.sleep(5000);
    }

    @Test
    public void testDropdown() throws InterruptedException {
        Select dropdown = new Select(driver.findElement(By.id("carselect")));
        //select by value
        dropdown.selectByValue("honda");
        Thread.sleep(1000);

        //select by index
        dropdown.selectByIndex(1);
        Thread.sleep(1000);

        //select by Visible
        dropdown.selectByVisibleText("BMW");
        Thread.sleep(5000);
    }

    @Test
    public void testAlert() throws InterruptedException {
        WebElement alertButton = driver.findElement(By.id("alertbtn"));
        alertButton.click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

    }

    @Test
    public void testConfirmAlert() throws InterruptedException {
        WebElement confirmButton = driver.findElement(By.id("confirmbtn"));
        confirmButton.click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void testTabs() throws InterruptedException {
        String currentHandler = driver.getWindowHandle();
        driver.findElement(By.id("opentab")).click();
        Thread.sleep(3000);
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(currentHandler)) {
                //Switch to the opened tab
                driver.switchTo().window(actual);
                System.out.println(driver.getCurrentUrl());
                driver.close();
                Thread.sleep(3000);
            }

        }
    }
    @Test
    public void testIframe() throws InterruptedException {
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//a[.='Sign In']")).click();
        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("login-password")).sendKeys("abcabc");
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
    }

    @Test
    public void testSuccesfulLogin() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("login-password")).sendKeys("abcabc");
        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'default-user-profile-pic.png')]")).isDisplayed());
    }

    @Test
    public void testErrorMessageForInvalidCredentials() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("test1@email.com");
        driver.findElement(By.id("login-password")).sendKeys("abcabc");
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.id("incorrectdetails")).getText().trim(),"Incorrect login details. Please try again.");
    }


}
