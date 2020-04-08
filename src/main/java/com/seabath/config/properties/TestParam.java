package com.seabath.config.properties;

import com.seabath.config.driver.builder.DriverBuilder;
import java.util.Properties;
import lombok.Getter;

@Getter
public class TestParam {


    public static final String DRIVER_BUILDER_PROPERTY_KEY = "driverBuilder";
    public static final String APP_URL_PROPERTY_KEY = "appUrl";
    public static final String PATH_DRIVER_PROPERTY_KEY = "pathDriver";

    private DriverBuilder<?> driverBuilder;
    private String appUrl;
    private String pathDriver;

    public void ingest(Properties properties) {
        final String driverBuilderProperty = properties.getProperty(DRIVER_BUILDER_PROPERTY_KEY);
        final DriverBuilder.DriverBuilders driverBuilders = DriverBuilder.DriverBuilders.valueOf(driverBuilderProperty);
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
