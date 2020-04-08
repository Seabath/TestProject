package com.seabath.ui.google.page;

import com.seabath.config.data.TestData;
import com.seabath.config.properties.TestParam;
import com.seabath.ui.common.BasePage;
import com.seabath.ui.elements.CustomWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleMainPage extends BasePage<RemoteWebDriver> {

    private final CustomWebElement<?> middleLogo;

    public GoogleMainPage(TestData<RemoteWebDriver> testData, TestParam<RemoteWebDriver> testParam) {
        super(testData, testParam);

        middleLogo = new CustomWebElement<>(
            testData.getWebDriver(),
            By.cssSelector("img")
        );
    }


    public GoogleMainPage clickLogo() {
        middleLogo.click();
        return this;
    }
}
