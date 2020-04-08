package com.seabath.endtoend;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.PropertyReader;
import static com.seabath.config.properties.PropertyReader.PARAM_FILE_PATH;
import com.seabath.config.properties.TestParam;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AbstractT<T extends TestDirector<?>> {

    @Getter
    private final TestParam testParam;
    @Getter
    private T director;

    public AbstractT() {
        final PropertyReader propertyReader =
            new PropertyReader(System.getProperty(PARAM_FILE_PATH));
        this.testParam = propertyReader.ingestVariables();
    }

    @BeforeEach
    public void initTest() {
        director = initDirector();

    }


    @AfterEach
    public void tearDownTest() {
        TestData<?> testData = director.getTestData();
        final RemoteWebDriver webDriver = testData.getWebDriver();
        if (webDriver != null && StringUtils.isNotBlank(webDriver.getSessionId().toString())) {
            webDriver.quit();
        }
    }

    protected abstract T initDirector();
}
