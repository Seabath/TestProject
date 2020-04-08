package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import org.openqa.selenium.WebDriver;

public class GeckoDriverBuilder implements DriverBuilder<WebDriver> {


    private final TestParam testParam;

    public GeckoDriverBuilder(TestParam testParam) {
        this.testParam = testParam;
    }

    public WebDriver buildDriver() {
        return null;
    }
}
