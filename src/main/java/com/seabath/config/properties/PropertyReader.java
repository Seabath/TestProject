package com.seabath.config.properties;

import io.qameta.allure.Step;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PropertyReader {
    public static final String PARAM_FILE_PATH = "paramFilePath";
    private static final String PATH_ALLURE_RESULT = System.getProperty("allure.results.directory");
    public static final String PATH_TO_ALLURE_ENVIRONNEMENT_PROPERTIES =
        PATH_ALLURE_RESULT + "/environment.properties";

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
            FileUtils.copyFile(file, new File(PATH_TO_ALLURE_ENVIRONNEMENT_PROPERTIES));
        } catch (IOException e) {
            throw new IllegalStateException("Could not read file: " + file, e);
        }
        return new TestParam<>(properties);
    }
}
