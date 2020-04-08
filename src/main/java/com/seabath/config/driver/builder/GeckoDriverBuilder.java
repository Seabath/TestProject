package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GeckoDriverBuilder implements DriverBuilder<FirefoxDriver> {


    private final TestParam testParam;

    public GeckoDriverBuilder(TestParam testParam) {
        this.testParam = testParam;
    }

    public FirefoxDriver buildDriver() {
        System.setProperty("webdriver.gecko.driver", testParam.getPathDriver());
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new FirefoxDriver(firefoxOptions);
    }
}
