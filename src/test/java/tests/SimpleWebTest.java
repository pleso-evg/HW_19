package tests;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleWebTest extends TestBase {

    @Test
    void openGoogleTest() {
        driver.get("https://google.com");
        assertThat(driver.getTitle()).containsIgnoringCase("google");
    }
}