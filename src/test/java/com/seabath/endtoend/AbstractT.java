package com.seabath.endtoend;

import com.seabath.config.director.TestDirector;
import com.seabath.config.properties.PropertyReader;
import static com.seabath.config.properties.PropertyReader.PARAM_FILE_PATH;
import com.seabath.config.properties.TestParam;
import com.seabath.config.watcher.ScreenshotCallBack;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AbstractT<T extends TestDirector<?, ?>> {

    @RegisterExtension
    public ScreenshotCallBack screenshotCallBack;

    @Getter
    private final TestParam<RemoteWebDriver> testParam;
    @Getter
    private T director;


    public AbstractT() {
        final PropertyReader propertyReader =
            new PropertyReader(System.getProperty(PARAM_FILE_PATH));
        this.testParam = propertyReader.ingestVariables();
        screenshotCallBack = new ScreenshotCallBack(testParam);
    }

    @BeforeEach
    public void initTest() {
        director = initDirector();
        screenshotCallBack.setDirector(director);
    }


    @AfterEach
    public void tearDownTest() {
        director.killDriver();
    }

    protected abstract T initDirector();
}
