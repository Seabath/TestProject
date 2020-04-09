package com.seabath.ui.common;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage<T extends RemoteWebDriver> {

    private final TestData<T> testData;
    private final TestParam<T> testParam;

    public BasePage(TestData<T> testData, TestParam<T> testParam) {
        this.testData = testData;
        this.testParam = testParam;
    }

    @Step
    public void end() {
        testParam.getDriverBuilder().killDriver(testData.getWebDriver());
    }
}
