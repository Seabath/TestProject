package com.seabath.ui.google.page;

import com.seabath.config.data.TestData;
import com.seabath.ui.elements.CustomWebElement;
import org.openqa.selenium.By;

public class GoogleMainPage {

    private final CustomWebElement<?> middleLogo;

    public GoogleMainPage(TestData<?> testData) {

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
