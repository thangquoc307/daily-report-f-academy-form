import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class TestClass {
    private ChromeDriver chromeDriver;
    @BeforeTest
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }
    @Test
    public void Run(){
        chromeDriver.get("https://docs.google.com/forms/d/e/1FAIpQLScuctZgK0hixrStEyPHhFKKV5t_LlR4-J8UPNY_gDcmPZU13Q/viewform");
        WebDriverWait wait1 = new WebDriverWait(chromeDriver,  Duration.ofSeconds(15));

        WebElement nameInput = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("input")));
        nameInput.sendKeys("ThangLQ14");

        WebElement className = wait1.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='Fresher JS 022024']")
                )
        );
        className.click();

        WebElement dateInput = wait1.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='date']")));
        String now = "" + LocalDate.now().getDayOfMonth() + LocalDate.now().getMonth() + "\t" + LocalDate.now().getYear();
        dateInput.sendKeys(now);

        List<WebElement> radioBoxList = wait1.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='5']")
                )
        );
        for (WebElement element : radioBoxList) {
            element.click();
        }

        List<WebElement> textareaList = wait1.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("textarea")));
        textareaList.get(0).sendKeys("Day hay");
        textareaList.get(1).sendKeys("Khong co");
        textareaList.get(2).sendKeys("Tot");
        textareaList.get(3).sendKeys("Khong co");
    }
}
