import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class seleniumSixTest {
    static ChromeDriver chromeDriver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    @Test
    public void checkamazonsearch() {
        chromeDriver.get("https://amazon.in/");

        WebElement searchInput = chromeDriver.findElement(By.cssSelector("[name='field-keywords']"));

        String searchPhrase = "iphone";

        searchInput.sendKeys(searchPhrase);
        searchInput.sendKeys(Keys.ENTER);

        List<String> actualItems = chromeDriver.findElements(By.cssSelector(".s-card-container")).stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList());

        List<String> expectedItems = actualItems.stream()
                .filter(item -> item.contains(searchPhrase))
                .collect(Collectors.toList());

        Assertions.assertEquals(expectedItems, actualItems);
    }

    @AfterAll
    public static void stopdpwnDriver() {
        //chromeDriver.quit();
    }
}
