package com.seabath.ui.google.director;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.google.page.GoogleMainPage;

public class GoogleTestDirector extends TestDirector<GoogleMainPage> {

    public GoogleTestDirector(TestParam testParam) {
        super(testParam);
    }

    @Override
    protected GoogleMainPage getFirstPage(TestData<?> testData) {
        testData.getWebDriver().get(getTestParam().getAppUrl());
        return new GoogleMainPage(testData);
    }
}
