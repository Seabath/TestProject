package com.seabath.ui.elements;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CustomWebElement<T extends RemoteWebDriver> implements WebElement {


    private final T driver;
    private final By by;

    public CustomWebElement(T driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    private WebElement getElement() {
        return driver.findElement(by);
    }

    @Override
    public void click() {
        getElement().click();
    }

    @Override
    public void submit() {
        getElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getElement().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        getElement().clear();
    }

    @Override
    public String getTagName() {
        return getElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return getElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    @Override
    public String getText() {
        return getElement().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return getElement().getScreenshotAs(target);
    }
}
