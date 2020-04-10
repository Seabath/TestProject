package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import io.qameta.allure.Step;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriverBuilder implements DriverBuilder<RemoteWebDriver> {
    private final TestParam<RemoteWebDriver> testParam;

    public RemoteDriverBuilder(TestParam<RemoteWebDriver> testParam) {
        this.testParam = testParam;
    }

    @Override
    @Step
    public RemoteWebDriver buildDriver() {
        RemoteWebDriver remoteWebDriver;
        final DesiredCapabilities capabilities = testParam.getDesiredCapabilities();
        try {
            remoteWebDriver = new RemoteWebDriver(new URL(testParam.getGridUrl()), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Malformed url", e);
        }

        remoteWebDriver.get(testParam.getAppUrl());
        return remoteWebDriver;
    }

    @Override
    @Step
    public void killDriver(RemoteWebDriver webDriver) {
        if (webDriver != null && webDriver.getSessionId() != null) {
            webDriver.quit();
        }
    }
}
