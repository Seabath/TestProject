package com.seabath.ui.common;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.atomic.AtomicReference;

@Getter
public class BasePage<T extends RemoteWebDriver, P extends BasePage<T, P>> {

    private final TestDirector<?, T> testDirector;
    protected final TestData<T> testData;
    private final P currentPage;

    public BasePage(TestDirector<?, T> testDirector, TestData<T> testData) {
        this.testDirector = testDirector;
        this.testData = testData;
        this.currentPage = (P) this;
    }


    @Step("Get current URL")
    public P retrieveCurrentUrl(AtomicReference<String> urlRef) {
        urlRef.set(testData.getWebDriver().getCurrentUrl());
        return currentPage;
    }

    @Step("Close driver")
    public void end() {
        testDirector.killDriverWithAttachments(testData);
    }
}
