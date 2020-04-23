package com.seabath.config.director;

import com.seabath.config.attachment.ScreenShooter;
import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.common.BasePage;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class TestDirector<T extends BasePage<U>, U extends RemoteWebDriver> {

    @Getter
    private final TestParam<U> testParam;

    private final List<TestData<U>> testDatas;

    public TestDirector(TestParam<U> testParam) {
        this.testParam = testParam;
        testDatas = new ArrayList<>();
    }

    @Step
    public T start() {
        final U webDriver = testParam.getDriverBuilder().buildDriver();
        TestData<U> testData = new TestData<>(webDriver);
        testDatas.add(testData);

        return getFirstPage(testData);
    }


    protected abstract T getFirstPage(TestData<U> testData);

    @Step
    public void killDriverWithAttachments(TestData<U> testData) {
        final U webDriver = testData.getWebDriver();
        if (testParam.isTakeScreenshot() && webDriver != null && webDriver.getSessionId() != null) {
            new ScreenShooter(webDriver).shootScreen();
        }
        testParam.getDriverBuilder().killDriver(webDriver);
        testDatas.remove(testData);
    }

    @Step
    public void killRemainingDriversWithAttachements() {
        testDatas.forEach(this::killDriverWithAttachments);
    }
}
