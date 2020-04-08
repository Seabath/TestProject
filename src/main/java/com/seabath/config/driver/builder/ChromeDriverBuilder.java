package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverBuilder implements DriverBuilder<ChromeDriver> {


    private final TestParam<ChromeDriver> testParam;

    public ChromeDriverBuilder(TestParam<ChromeDriver> testParam) {
        this.testParam = testParam;
    }

    public ChromeDriver buildDriver() {
        System.setProperty("webdriver.chrome.driver", testParam.getPathDriver());
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions);
    }

    @Override
    public void killDriver(ChromeDriver webDriver) {
        if (webDriver != null && webDriver.getSessionId() != null &&
            StringUtils.isNotBlank(webDriver.getSessionId().toString())) {
            webDriver.quit();
        }
    }
}
