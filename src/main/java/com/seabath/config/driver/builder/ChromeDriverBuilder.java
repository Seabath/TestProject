package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import io.qameta.allure.Step;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverBuilder implements DriverBuilder<ChromeDriver> {


    private final TestParam<ChromeDriver> testParam;

    public ChromeDriverBuilder(TestParam<ChromeDriver> testParam) {
        this.testParam = testParam;
    }

    @Override
    @Step
    public ChromeDriver buildDriver() {
        System.setProperty("webdriver.chrome.driver", testParam.getPathDriver());
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions);
    }

    @Override
    @Step
    public void killDriver(ChromeDriver webDriver) {
        if (webDriver != null && webDriver.getSessionId() != null &&
            StringUtils.isNotBlank(webDriver.getSessionId().toString())) {
            webDriver.quit();
        }
    }
}
