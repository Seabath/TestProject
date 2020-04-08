package com.seabath.config.properties;

import com.seabath.config.driver.builder.DriverBuilder;
import java.util.Properties;
import lombok.Getter;
import org.openqa.selenium.remote.RemoteWebDriver;

@Getter
public class TestParam<U extends RemoteWebDriver> {


    public static final String DRIVER_BUILDER_PROPERTY_KEY = "driverBuilder";
    public static final String APP_URL_PROPERTY_KEY = "appUrl";
    public static final String PATH_DRIVER_PROPERTY_KEY = "pathDriver";

    private DriverBuilder<U> driverBuilder;
    private String appUrl;
    private String pathDriver;

    public TestParam(Properties properties) {
        final String driverBuilderProperty = properties.getProperty(DRIVER_BUILDER_PROPERTY_KEY);
        try {
            final DriverBuilder.DriverBuilders builder =
                DriverBuilder.DriverBuilders.valueOf(driverBuilderProperty);
            this.driverBuilder = builder.getBuilder(this);
        } catch (IllegalArgumentException e) {
            final String message = String.format("Couldn't find driver builder type: <%s>.",
                driverBuilderProperty);
            throw new IllegalStateException(message, e);
        }
        this.appUrl = properties.getProperty(APP_URL_PROPERTY_KEY);
        this.pathDriver = properties.getProperty(PATH_DRIVER_PROPERTY_KEY);
    }
}
