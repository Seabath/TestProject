package com.seabath.ui.google.page;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.common.BaseElement;
import com.seabath.ui.common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleMainPage extends BasePage<RemoteWebDriver> {

    private final BaseElement<?, ?> middleLogo;

    public GoogleMainPage(TestData<RemoteWebDriver> testData, TestParam<RemoteWebDriver> testParam) {
        super(testData, testParam);

        middleLogo = new BaseElement<>(
            testData.getWebDriver(),
            testData.getWebDriver(),
            By.cssSelector("img")
        );
    }

    @Step("Click on main logo")
    public GoogleMainPage clickLogo() {
        middleLogo
            .waitVisible()
            .click();
        return this;
    }
}
