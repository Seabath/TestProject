package com.seabath.ui.common;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static org.assertj.core.api.Fail.fail;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement<T extends RemoteWebDriver> {

    private static final Duration DEFAULT_TIMEOUT = Duration.of(20, ChronoUnit.SECONDS);
    private final T driver;
    private final By by;
    private WebElement webElement;

    public BaseElement(T driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    public void click() {
        getElement().click();
    }

    public BaseElement<T> waitVisible() {
        return waitVisible(DEFAULT_TIMEOUT);
    }

    public BaseElement<T> waitVisible(Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                .until(webDriver -> getElement());
        } catch (TimeoutException e) {
            //noinspection ResultOfMethodCallIgnored
            fail(String.format("Element with selector: <%s> not found.", by.toString()), e);
        }

        return this;
    }

    protected WebElement getElement() {
        if (this.webElement != null) {
            return webElement;
        }
        try {
            webElement = driver.findElement(by);
        } catch (StaleElementReferenceException | ElementNotVisibleException e) {
            //noinspection ResultOfMethodCallIgnored
            fail(String.format("Element with selector: <%s> not found.", by.toString()), e);
        }
        return webElement;
    }
}
