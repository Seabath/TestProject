package com.seabath.ui.datadome.page;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.ui.common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class KeyHelpPage<T> extends BasePage<RemoteWebDriver, KeyHelpPage<T>> {

    private final T previousPage;
    private final String previousTab;

    public KeyHelpPage(TestDirector<?, RemoteWebDriver> testDirector, TestData<RemoteWebDriver> testData, T previousPage, String previousTab) {
        super(testDirector, testData);
        this.previousPage = previousPage;
        this.previousTab = previousTab;
    }

    @Step
    public T closeTab() {
        testData.getWebDriver().switchTo().window(previousTab);
        return previousPage;
    }

}
