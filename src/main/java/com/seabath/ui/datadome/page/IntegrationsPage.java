package com.seabath.ui.datadome.page;

import com.seabath.config.data.TestData;
import com.seabath.config.director.TestDirector;
import com.seabath.ui.common.BaseElement;
import com.seabath.ui.common.BasePage;
import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Fail.fail;

public class IntegrationsPage extends BasePage<RemoteWebDriver, IntegrationsPage> {

    private final BaseElement<RemoteWebDriver, RemoteWebDriver> keyTable;

    private static final Duration DEFAULT_TIMEOUT = Duration.of(2, ChronoUnit.MINUTES);
    private static final Duration DEFAULT_DELAY = Duration.of(500, ChronoUnit.MILLIS);
    private static final Integer DEFAULT_MAX_RETRIES = 40;


    public IntegrationsPage(TestDirector<?, RemoteWebDriver> testDirector, TestData<RemoteWebDriver> testData) {
        super(testDirector, testData);

        keyTable = new BaseElement<>(
                testData.getWebDriver(),
                testData.getWebDriver(),
                By.cssSelector("[cardtitle=Keys]")
        );
    }

    @Step("Retrieve key GUI at index {0}")
    public IntegrationsPage retrieveKeyGuid(int tableIndex, AtomicReference<String> guidRef) {
        BaseElement<?, ?> guidColumn = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(2)", tableIndex + 1))
        );
        guidRef.set(guidColumn.getText());
        return this;
    }


    @Step("Retrieve key name at index {0}")
    public IntegrationsPage retrieveName(int tableIndex, AtomicReference<String> nameRef) {
        BaseElement<?, ?> nameColumn = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(1)", tableIndex + 1))
        );
        nameRef.set(nameColumn.getText());
        return this;
    }

    @Step("Retrieve key type at index {0}")
    public IntegrationsPage retrieveType(int tableIndex, AtomicReference<String> typeRef) {
        BaseElement<?, ?> typeColumn = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(1)", tableIndex + 1))
        );
        typeRef.set(typeColumn.getText());
        return this;
    }

    @Step("Retrieve key status at index {0}")
    public IntegrationsPage retrieveStatus(int tableIndex, AtomicReference<String> statusRef) {
        BaseElement<?, ?> statusColumn = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(3)", tableIndex + 1))
        );
        statusRef.set(statusColumn.getText());
        return this;
    }

    @Step("Retrieve key last activity at index {0}")
    public IntegrationsPage retrieveLastActivity(int tableIndex, AtomicReference<String> lastActivityRef) {
        BaseElement<?, ?> lastActivityColumn = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(2)", tableIndex + 1))
        );
        lastActivityRef.set(lastActivityColumn.getText());
        return this;
    }

    @Step("Retrieve key creation date at index {0}")
    public IntegrationsPage retrieveCreationDate(int tableIndex, AtomicReference<String> creationDateRef) {
        BaseElement<?, ?> creationDateColumn = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(2)", tableIndex + 1))
        );
        creationDateRef.set(creationDateColumn.getText());
        return this;
    }

    @Step("Click key documentation at index {0}")
    public KeyHelpPage<IntegrationsPage> clickKeyDocumentation(int tableIndex) {
        BaseElement<?, ?> infoButton = new BaseElement<>(
                testData.getWebDriver(),
                keyTable,
                By.cssSelector(String.format("mat-row:nth-of-type(%d) mat-cell:nth-of-type(1) dd-icon", tableIndex + 1))
        );

        RemoteWebDriver webDriver = testData.getWebDriver();
        String currentTab = webDriver.getWindowHandle();
        infoButton.click();


        Failsafe.with(RetryPolicy.builder()
                        .withDelay(DEFAULT_DELAY)
                        .withMaxDuration(DEFAULT_TIMEOUT)
                        .withMaxRetries(DEFAULT_MAX_RETRIES)
                        .handleResult(1)
                        .build())
                .onFailure(event -> fail("No other tab was open", event.getException()))
                .get(() -> webDriver.getWindowHandles().size());

        return new KeyHelpPage<>(getTestDirector(), getTestData(), this, currentTab);
    }
}
