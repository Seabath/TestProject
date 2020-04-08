package com.seabath.endtoend;

import com.seabath.config.properties.PropertyReader;
import static com.seabath.config.properties.PropertyReader.PARAM_FILE_PATH;
import com.seabath.config.properties.TestParam;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AbstractT {

    private final TestParam testParam;

    public AbstractT() {
        final PropertyReader propertyReader =
            new PropertyReader(System.getProperty(PARAM_FILE_PATH));
        this.testParam = propertyReader.ingestVariables();
    }

    @BeforeEach
    public void initTest() {

    }


    @AfterEach
    public void tearDownTest() {

    }
}
