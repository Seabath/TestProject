package com.seabath.config.watcher;

import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.TestParam;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import lombok.Setter;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ScreenshotCallBack implements AfterTestExecutionCallback {

    @Setter
    private TestDirector<?, ?> director;
    private final TestParam<RemoteWebDriver> testParam;

    public ScreenshotCallBack(TestParam<RemoteWebDriver> testParam) {
        this.testParam = testParam;
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (director == null) {
            return;
        }
        final RemoteWebDriver webDriver = director.getTestData().getWebDriver();
        if (webDriver == null || webDriver.getSessionId() == null || !testParam.isTakeScreenshot()) {
            return;
        }

        final byte[] screenshotAs = webDriver.getScreenshotAs(OutputType.BYTES);
        final InputStream inputStream = new ByteArrayInputStream(screenshotAs);
        Allure.addAttachment("Screenshot", inputStream);
    }
}
