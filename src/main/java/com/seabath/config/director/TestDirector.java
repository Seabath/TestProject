package com.seabath.config.director;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class TestDirector<T, U extends RemoteWebDriver> {

    @Getter
    private final TestParam<U> testParam;
    @Getter
    private TestData<U> testData;

    public TestDirector(TestParam testParam) {
        this.testParam = testParam;
    }

    public T start() {
        final U webDriver = testParam.getDriverBuilder().buildDriver();
        testData = new TestData<>(webDriver);

        return getFirstPage(testData);
    }


    protected abstract T getFirstPage(TestData<?> testData);

    public void killDriver() {
        testParam.getDriverBuilder().killDriver(testData.getWebDriver());
    }
}
