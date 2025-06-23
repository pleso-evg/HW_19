package tests;

import config.WebDriverConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleWebTest extends TestBase {

    @Test
    public void testGoogleEqualsTitle() {
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);
        WebDriver driver;
        if (config.isRemote()) {
            System.out.println("Запуск в Selenoid");
            ChromeOptions options = new ChromeOptions();
            options.setBrowserVersion(config.browserVersion());
            driver = new RemoteWebDriver(config.getRemoteURL(), options);
        } else {
            System.out.println("Локальный запуск");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get("https://google.com");
        assertEquals("Google", driver.getTitle());
    }
}