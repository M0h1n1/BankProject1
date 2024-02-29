import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class sample {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
                driver.get("https://www.letskodeit.com/practice");
        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("login-Password")).sendKeys("abcabc");
        driver.quit();
    }
}
