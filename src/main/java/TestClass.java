import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestClass {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String nihongoCsv = "E:\\fpt-academy\\daily-report-f-academy-form\\src\\main\\java\\nihongo.csv";
    private String codeCsv = "E:\\fpt-academy\\daily-report-f-academy-form\\src\\main\\java\\code.csv";

    public static void fillForm(String name, boolean isTrainer, LocalDate date, String monhoc) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();

        chromeDriver.get("https://docs.google.com/forms/d/e/1FAIpQLScuctZgK0hixrStEyPHhFKKV5t_LlR4-J8UPNY_gDcmPZU13Q/viewform");
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(5));

        WebElement className = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='Fresher JS 022024']")
                )
        );
        className.click();

        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='date']")));

        String formattedDate = "" + date.getDayOfMonth() + date.getMonth() + "\t" + date.getYear();
        dateInput.sendKeys(formattedDate);

        List<WebElement> radioBoxList = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='5']")
                )
        );
        for (WebElement element : radioBoxList) {
            element.click();
        }

        WebElement teacher = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='"
                                + name + "']")
                )
        );
        teacher.click();

        WebElement trainer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='"
                                + (isTrainer ? "Trainer" : "Mentor") + "']")
                )
        );
        trainer.click();

        WebElement monhocCheck = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'bzfPab wFGF8')]//span[text()='"
                                + monhoc + "']")
                )
        );
        monhocCheck.click();

        List<WebElement> textareaList = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("textarea")));
        textareaList.get(0).sendKeys("Day hay, nhiet tinh");
        textareaList.get(1).sendKeys("Khong co");
        textareaList.get(2).sendKeys("Tot");
        textareaList.get(3).sendKeys("Khong co");

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='whsOnd zHQkBf' and @jsname='YPqjbf']")));
        nameInput.sendKeys("ThangLQ14");
    }

    @Test
    public void runData() {
        try {
//            List<String> data = Files.readAllLines(Path.of(nihongoCsv));
            List<String> data = Files.readAllLines(Path.of(codeCsv));
//            for (String dataStr : data) {
            String dataStr = data.get(17);
                String[] array = dataStr.split(",");

                String name = array[0];
                String monhoc = array[3];
                boolean isTrainer = Boolean.parseBoolean(array[1]);
                LocalDate date = LocalDate.parse(array[2], formatter);

                fillForm(name, isTrainer, date, monhoc);
//                Thread.sleep(10000);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
