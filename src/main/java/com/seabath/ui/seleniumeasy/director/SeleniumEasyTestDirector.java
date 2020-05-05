package com.seabath.ui.seleniumeasy.director;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.google.page.GoogleMainPage;
import com.seabath.ui.seleniumeasy.page.TableSearchFilterDemoPage;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumEasyTestDirector extends TestDirector<TableSearchFilterDemoPage, RemoteWebDriver> {

    public SeleniumEasyTestDirector(TestParam<RemoteWebDriver> testParam) {
        super(testParam);
    }

    @Override
    @Step
    protected TableSearchFilterDemoPage getFirstPage(TestData<RemoteWebDriver> testData) {
        testData.getWebDriver().get(getTestParam().getAppUrl());
        testData.getWebDriver().manage().window().fullscreen();
        return new TableSearchFilterDemoPage(testData, this);
    }
}
