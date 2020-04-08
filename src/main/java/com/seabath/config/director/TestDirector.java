package com.seabath.config.director;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class TestDirector<T> {

    @Getter
    private final TestParam testParam;
    @Getter
    private TestData<?> testData;

    public TestDirector(TestParam testParam) {
        this.testParam = testParam;
    }

    public T start() {
        final RemoteWebDriver webDriver = testParam.getDriverBuilder().buildDriver();
        testData = new TestData<>(webDriver);

        return getFirstPage(testData);
    }


    protected abstract T getFirstPage(TestData<?> testData);

}
