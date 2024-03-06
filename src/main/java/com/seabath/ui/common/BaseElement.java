package com.seabath.ui.common;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Fail.fail;

public class BaseElement<T extends SearchContext, U extends RemoteWebDriver> implements SearchContext {

    private static final Duration DEFAULT_TIMEOUT = Duration.of(2, ChronoUnit.MINUTES);
    private static final Duration DEFAULT_DELAY = Duration.of(500, ChronoUnit.MILLIS);
    private static final Integer DEFAULT_MAX_RETRIES = 40;
    private final U driver;
    private final T searchContext;
    private final By by;

    public BaseElement(U driver, T searchContext, By by) {
        this.driver = driver;
        this.searchContext = searchContext;
        this.by = by;
    }

    @Step
    public void click() {
        waitVisible().click();
    }

    @Step
    public BaseElement<T, U> sendKeys(String keys) {
        Failsafe.with(RetryPolicy.builder()
                        .withDelay(DEFAULT_DELAY)
                        .withMaxDuration(DEFAULT_TIMEOUT)
                        .withMaxRetries(DEFAULT_MAX_RETRIES)
                        .build())
                .run(() -> waitVisible().sendKeys(keys));
        return this;
    }

    @Step
    public long getDisplayedNumber() {
        return searchContext.findElements(by).stream()
                .filter(WebElement::isDisplayed)
                .count();
    }


    public BaseElement<T, U> waitInvisible() {
        return waitInvisible(DEFAULT_TIMEOUT);
    }

    @Step
    public WebElement waitVisible() {
        return Failsafe.with(RetryPolicy.builder()
                        .withDelay(DEFAULT_DELAY)
                        .withMaxDuration(DEFAULT_TIMEOUT)
                        .withMaxRetries(DEFAULT_MAX_RETRIES)
                        .handleResult(null)
                        .build())
                .onFailure(event ->
                        fail(String.format("Element with selector: <%s> not found.", by.toString()), event.getException()))
                .get(this::getElement);
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
        return waitVisible().findElements(by);
    }

    @Override
    @Step
    public WebElement findElement(By by) {
        return waitVisible().findElement(by);
    }

    @Step
    protected WebElement getElement() {
        Failsafe.with(RetryPolicy.builder()
                        .handleResultIf(result -> !Boolean.parseBoolean(result.toString()))
                        .withDelay(DEFAULT_DELAY)
                        .withMaxDuration(DEFAULT_TIMEOUT)
                        .withMaxRetries(DEFAULT_MAX_RETRIES)
                        .build())
                .onFailure(event ->
                        fail(String.format("Page never loaded after %s", DEFAULT_TIMEOUT.toString()), event.getException()))
                .get(() -> driver.executeScript("return document.readyState").equals("complete"));

        return searchContext.findElement(by);
    }

    @Step
    public String getText() {
        return waitVisible().getText();
    }
}
