package com.seabath.ui.common;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage<T extends RemoteWebDriver> {

    private final TestDirector<?, T> testDirector;
    private final TestData<T> testData;

    public BasePage(TestDirector<?, T> testDirector, TestData<T> testData) {
        this.testDirector = testDirector;
        this.testData = testData;
    }

    @Step
    public void end() {
        testDirector.killDriverWithAttachments(testData);
    }
}
