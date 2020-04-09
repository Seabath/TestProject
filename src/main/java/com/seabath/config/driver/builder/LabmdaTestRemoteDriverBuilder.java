package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import io.qameta.allure.Step;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LabmdaTestRemoteDriverBuilder implements DriverBuilder<RemoteWebDriver> {

    // link to documentation
    // https://www.lambdatest.com/support/docs/junit-with-selenium-running-junit-automation-scripts-on-lambdatest-selenium-grid/

    private final TestParam<RemoteWebDriver> testParam;

    public LabmdaTestRemoteDriverBuilder(TestParam<RemoteWebDriver> testParam) {
        this.testParam = testParam;
    }

    @Override
    @Step
    public RemoteWebDriver buildDriver() {
        RemoteWebDriver remoteWebDriver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
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
