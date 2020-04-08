package com.seabath.config.director;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.common.BasePage;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class TestDirector<T extends BasePage<U>, U extends RemoteWebDriver> {

    @Getter
    private final TestParam<U> testParam;
    @Getter
    private TestData<U> testData;

    public TestDirector(TestParam<U> testParam) {
        this.testParam = testParam;
    }

    public T start() {
        final U webDriver = testParam.getDriverBuilder().buildDriver();
        testData = new TestData<>(webDriver);

        return getFirstPage(testData);
    }


    protected abstract T getFirstPage(TestData<U> testData);

    public void killDriver() {
        testParam.getDriverBuilder().killDriver(testData.getWebDriver());
    }
}
