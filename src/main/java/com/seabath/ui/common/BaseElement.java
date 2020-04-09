package com.seabath.ui.common;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
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
        webElement.click();
    }

    public BaseElement<T> waitVisible() {
        return waitVisible(DEFAULT_TIMEOUT);
    }

    public BaseElement<T> waitVisible(Duration timeout) {
        try {
            webElement = new WebDriverWait(driver, timeout)
                .until(webDriver -> webDriver.findElement(by));
        } catch (TimeoutException e) {
            fail(String.format("Element with selector: <%s> not found.", by.toString()));
        }

        this.webElement = driver.findElement(by);
        return this;
    }
}
