package com.seabath.config.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static final String PARAM_FILE_PATH = "paramFilePath";

    private final String propertyFilePath;

    public PropertyReader(String propertyFilePath) {
        this.propertyFilePath = propertyFilePath;
    }

    public TestParam ingestVariables() {
        Properties properties = new Properties();
        final File file = new File(propertyFilePath);
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new IllegalStateException("Could not read file: " + file, e);
        }

        final TestParam testParam = new TestParam();
        testParam.ingest(properties);
        return testParam;
    }
}
