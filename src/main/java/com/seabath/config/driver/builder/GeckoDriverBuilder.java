package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import io.qameta.allure.Step;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GeckoDriverBuilder implements DriverBuilder<FirefoxDriver> {


    private final TestParam<FirefoxDriver> testParam;

    public GeckoDriverBuilder(TestParam<FirefoxDriver> testParam) {
        this.testParam = testParam;
    }

    @Override
    @Step
    public FirefoxDriver buildDriver() {
        System.setProperty("webdriver.gecko.driver", testParam.getPathDriver());
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new FirefoxDriver(firefoxOptions);
    }

    @Override
    @Step
    public void killDriver(FirefoxDriver webDriver) {
        if (webDriver != null && StringUtils.isNotBlank(webDriver.getSessionId().toString())) {
            webDriver.quit();
        }
    }
}
