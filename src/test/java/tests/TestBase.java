package tests;

import config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public abstract class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new WebDriverProvider().get();
        driver.manage().timeouts().pageLoadTimeout(
                new WebDriverProvider().getConfig().pageLoadTimeout(),
                TimeUnit.MILLISECONDS
        );
        driver.manage().timeouts().implicitlyWait(
                new WebDriverProvider().getConfig().implicitWait(),
                TimeUnit.MILLISECONDS
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
