package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import org.openqa.selenium.WebDriver;

public interface DriverBuilder<T extends WebDriver> {

    T buildDriver();

    enum DriverBuilders {
        GECKO {
            public DriverBuilder<?> getBuilder(TestParam testParam) {
                return new GeckoDriverBuilder(testParam);
            }
        };

        public abstract DriverBuilder<?> getBuilder(TestParam testParam);
    }
}
