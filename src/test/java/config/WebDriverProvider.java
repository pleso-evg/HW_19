package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverProvider {
    private final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    // Добавляем геттер для конфига
    public WebDriverConfig getConfig() {
        return config;
    }

    public WebDriver get() {
        try {
            if (config.isRemote()) {
                return createRemoteWebDriver();
            } else {
                return createLocalWebDriver();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver: " + e.getMessage(), e);
        }
    }


    private WebDriver createRemoteWebDriver() {
        switch (config.browser()) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion(config.browserVersion());
                return new RemoteWebDriver(config.remoteUrl(), chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBrowserVersion(config.browserVersion());
                return new RemoteWebDriver(config.remoteUrl(), firefoxOptions);
            default:
                throw new RuntimeException("Unsupported browser for remote: " + config.browser());
        }
    }

    private WebDriver createLocalWebDriver() {
        return switch (config.browser()) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new org.openqa.selenium.chrome.ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new org.openqa.selenium.firefox.FirefoxDriver();
            }
        };
    }
}