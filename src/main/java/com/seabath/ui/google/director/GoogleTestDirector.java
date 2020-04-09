package com.seabath.ui.google.director;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.google.page.GoogleMainPage;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleTestDirector extends TestDirector<GoogleMainPage, RemoteWebDriver> {

    public GoogleTestDirector(TestParam<RemoteWebDriver> testParam) {
        super(testParam);
    }

    @Override
    protected GoogleMainPage getFirstPage(TestData<RemoteWebDriver> testData) {
        testData.getWebDriver().get(getTestParam().getAppUrl());
        testData.getWebDriver().manage().window().maximize();
        return new GoogleMainPage(testData, getTestParam());
    }
}
