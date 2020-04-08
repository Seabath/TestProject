package com.seabath.config.data;

import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestData<T extends RemoteWebDriver> {

    @Getter
    private final T webDriver;

    public TestData(T webDriver) {
        this.webDriver = webDriver;
    }
}
