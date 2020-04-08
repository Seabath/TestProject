package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverBuilder implements DriverBuilder<ChromeDriver> {


    private final TestParam testParam;

    public ChromeDriverBuilder(TestParam testParam) {
        this.testParam = testParam;
    }

    public ChromeDriver buildDriver() {
        System.setProperty("webdriver.chrome.driver", testParam.getPathDriver());
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions);
    }
}
