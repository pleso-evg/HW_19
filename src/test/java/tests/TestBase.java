package tests;

import config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public abstract class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new WebDriverProvider().get();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
