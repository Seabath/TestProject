package com.seabath.ui.common;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage<T extends RemoteWebDriver> {

    private final TestData<T> testData;
    private final TestParam<T> testParam;

    public BasePage(TestData<T> testData, TestParam<T> testParam) {
        this.testData = testData;
        this.testParam = testParam;
    }

    public void execute() {
        testParam.getDriverBuilder().killDriver(testData.getWebDriver());
    }
}
