package com.seabath.config.properties;

import io.qameta.allure.Step;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PropertyReader {
    public static final String PARAM_FILE_PATH = "paramFilePath";

    private final String propertyFilePath;

    public PropertyReader(String propertyFilePath) {
        this.propertyFilePath = propertyFilePath;
    }

    @Step
    public TestParam<RemoteWebDriver> ingestVariables() {
        Properties properties = new Properties();
        final File file = new File(propertyFilePath);
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new IllegalStateException("Could not read file: " + file, e);
        }

        return new TestParam<>(properties);
    }
}
