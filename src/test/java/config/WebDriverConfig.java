package config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface WebDriverConfig extends Config {

    @Key("pageLoadTimeout")
    @DefaultValue("30000")
    long pageLoadTimeout();

    @Key("implicitWait")
    @DefaultValue("5000")
    long implicitWait();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("version")
    @DefaultValue("127.0")
    String browserVersion();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    URL remoteUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
}
