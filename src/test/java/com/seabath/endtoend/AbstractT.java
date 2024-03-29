package com.seabath.endtoend;

import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.PropertyReader;
import static com.seabath.config.properties.PropertyReader.PARAM_FILE_PATH;
import com.seabath.config.properties.TestParam;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AbstractT<T extends TestDirector<?, ?>> {

    @Getter
    private final TestParam<RemoteWebDriver> testParam;
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
        if (director != null) {
            director.killRemainingDriversWithAttachments();
        }
    }

    protected abstract T initDirector();
}
