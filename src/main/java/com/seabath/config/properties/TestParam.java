package com.seabath.config.properties;

import com.seabath.config.driver.builder.DriverBuilder;
import java.util.Properties;
import lombok.Getter;

public class TestParam {


    public static final String DRIVER_BUILDER_PROPERTY = "driverBuilder";

    @Getter
    private DriverBuilder<?> driverBuilder;

    public void ingest(Properties properties) {
        final String driverBuilderProperty = properties.getProperty(DRIVER_BUILDER_PROPERTY);
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
    }
}
