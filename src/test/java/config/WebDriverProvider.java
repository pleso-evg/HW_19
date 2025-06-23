package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.function.Supplier;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;



public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());


    public WebDriver get() {

        if (config.isRemote()) {
            return createRemoteWebDriver();
        } else {
            return createLocalWebDriver();
        }
    }

    private WebDriver createRemoteWebDriver() {
        switch (config.getBrowser()) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion(config.browserVersion());
                return new RemoteWebDriver(config.getRemoteURL(), chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBrowserVersion(config.browserVersion());
                return new RemoteWebDriver(config.getRemoteURL(), firefoxOptions);
            default:
                throw new RuntimeException("Unsupported browser: " + config.getBrowser());
        }
    }

    private WebDriver createLocalWebDriver() {
        switch (config.getBrowser()) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new org.openqa.selenium.chrome.ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new org.openqa.selenium.firefox.FirefoxDriver();
            }
            default -> throw new RuntimeException("Unsupported browser: " + config.getBrowser());
        }
    }
}