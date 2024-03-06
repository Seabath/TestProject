package com.seabath.ui.datadome.director;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.datadome.page.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DataDomeTestDirector extends TestDirector<LoginPage, RemoteWebDriver> {

    public DataDomeTestDirector(TestParam<RemoteWebDriver> testParam) {
        super(testParam);
    }

    @Override
    @Step
    protected LoginPage getFirstPage(TestData<RemoteWebDriver> testData) {
        testData.getWebDriver().get(getTestParam().getAppUrl());
        testData.getWebDriver().manage().window().maximize();
        return new LoginPage(this, testData);
    }
}
