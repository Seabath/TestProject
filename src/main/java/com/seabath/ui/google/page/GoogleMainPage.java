package com.seabath.ui.google.page;

import com.seabath.config.data.TestData;
import com.seabath.ui.common.BaseElement;
import com.seabath.ui.common.BasePage;
import com.seabath.ui.google.director.GoogleTestDirector;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleMainPage extends BasePage<RemoteWebDriver, GoogleMainPage> {

    private final BaseElement<?, ?> middleLogo;

    public GoogleMainPage(TestData<RemoteWebDriver> testData, GoogleTestDirector testDirector) {
        super(testDirector, testData);

        middleLogo = new BaseElement<>(
            testData.getWebDriver(),
            testData.getWebDriver(),
            By.cssSelector("img")
        );
    }

    @Step("Click on main logo")
    public GoogleMainPage clickLogo() {
        middleLogo.click();
        return this;
    }
}
