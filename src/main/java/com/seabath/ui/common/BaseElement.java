package com.seabath.ui.common;

import io.qameta.allure.Step;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static org.assertj.core.api.Fail.fail;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement<T extends SearchContext, U extends RemoteWebDriver> implements SearchContext {

    private static final Duration DEFAULT_TIMEOUT = Duration.of(20, ChronoUnit.SECONDS);
    private final U driver;
    private final T searchContext;
    private final By by;
    private WebElement webElement;

    public BaseElement(U driver, T searchContext, By by) {
        this.driver = driver;
        this.searchContext = searchContext;
        this.by = by;
    }

    @Step
    public void click() {
        getElement().click();
    }

    @Step
    public BaseElement<T, U> sendKeys(String number) {
        getElement().sendKeys(number);
        return this;
    }

    public long getDisplayedNumber() {
        return searchContext.findElements(by).stream()
                .filter(WebElement::isDisplayed)
                .count();
    }

    @Step
    public BaseElement<T, U> waitVisible() {
        return waitVisible(DEFAULT_TIMEOUT);
    }

    public BaseElement<T, U> waitInvisible() {
        return waitInvisible(DEFAULT_TIMEOUT);
    }

    @Step
    public BaseElement<T, U> waitVisible(Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                .until(webDriver -> getElement());
        } catch (TimeoutException e) {
            //noinspection ResultOfMethodCallIgnored
            fail(String.format("Element with selector: <%s> not found.", by.toString()), e);
        }

        return this;
    }
    @Step
    public BaseElement<T, U> waitInvisible(Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            //noinspection ResultOfMethodCallIgnored
            fail(String.format("Element with selector: <%s> still visible.", by.toString()), e);
        }

        return this;
    }

    @Override
    @Step
    public List<WebElement> findElements(By by) {
        return getElement().findElements(by);
    }

    @Override
    @Step
    public WebElement findElement(By by) {
        return getElement().findElement(by);
    }

    @Step
    protected WebElement getElement() {
        if (this.webElement != null) {
            return webElement;
        }
        try {
            webElement = searchContext.findElement(by);
        } catch (StaleElementReferenceException | ElementNotVisibleException e) {
            //noinspection ResultOfMethodCallIgnored
            fail(String.format("Element with selector: <%s> not found.", by.toString()), e);
        }
        return webElement;
    }
}
