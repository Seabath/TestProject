package com.seabath.ui.datadome.page;

import com.seabath.config.data.TestData;
import com.seabath.ui.common.BaseElement;
import com.seabath.ui.common.BasePage;
import com.seabath.ui.datadome.director.DataDomeTestDirector;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends BasePage<RemoteWebDriver, LoginPage> {


    private final BaseElement<?, ?> loginTextBox;
    private final BaseElement<?, ?> passwordTextBox;
    private final BaseElement<?, ?> submitButton;

    public LoginPage(DataDomeTestDirector testDirector, TestData<RemoteWebDriver> testData) {
        super(testDirector, testData);
        loginTextBox = new BaseElement<>(
                testData.getWebDriver(),
                testData.getWebDriver(),
                By.cssSelector("input[type=email]")
        );
        passwordTextBox = new BaseElement<>(
                testData.getWebDriver(),
                testData.getWebDriver(),
                By.cssSelector("input[type=password]")
        );
        submitButton = new BaseElement<>(
                testData.getWebDriver(),
                testData.getWebDriver(),
                By.cssSelector(".login__form button")
        );
    }

    @Step("Fill login text box with text {0}")
    public LoginPage fillLogin(String login) {
        loginTextBox.sendKeys(login);
        return this;
    }

    @Step("Fill password text box with text {0}")
    public LoginPage fillPassword(String password) {
        passwordTextBox.sendKeys(password);
        return this;
    }

    @Step("Click login button expecting success")
    public IntegrationsPage loginSuccessfully() {
        submitButton.click();
        return new IntegrationsPage(getTestDirector(), getTestData());
    }
}
