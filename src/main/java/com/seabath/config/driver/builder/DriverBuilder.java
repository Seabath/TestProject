package com.seabath.config.driver.builder;

import com.seabath.config.properties.TestParam;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverBuilder<T extends RemoteWebDriver> {

    T buildDriver();

    enum DriverBuilders {
        GECKO {
            @Override
            public GeckoDriverBuilder getBuilder(TestParam testParam) {
                return new GeckoDriverBuilder(testParam);
            }
        },
        CHROME {
            @Override
            public ChromeDriverBuilder getBuilder(TestParam testParam) {
                return new ChromeDriverBuilder(testParam);
            }
        };

        public abstract DriverBuilder<?> getBuilder(TestParam testParam);
    }
}
