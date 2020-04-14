package com.seabath.ui.common;

import com.seabath.config.director.TestDirector;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage<T extends RemoteWebDriver> {

    private final TestDirector<?, ?> testDirector;

    public BasePage(TestDirector<?, ?> testDirector) {
        this.testDirector = testDirector;
    }

    @Step
    public void end() {
        testDirector.killDriverWithAttachments();
    }
}
