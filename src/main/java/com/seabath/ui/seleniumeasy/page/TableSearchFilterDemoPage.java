package com.seabath.ui.seleniumeasy.page;

import com.seabath.config.data.TestData;
import com.seabath.ui.common.BaseElement;
import com.seabath.ui.common.BasePage;
import com.seabath.ui.seleniumeasy.director.SeleniumEasyTestDirector;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.atomic.AtomicReference;

public class TableSearchFilterDemoPage extends BasePage<RemoteWebDriver, TableSearchFilterDemoPage> {

    private final BaseElement<?, ?> buttonFilter;
    private final BaseElement<?, ?> filterTable;
    private final BaseElement<?, ?> filterId;
    private final BaseElement<?, ?> filterUserName;
    private final BaseElement<?, ?> filterFirstName;
    private final BaseElement<?, ?> filterLastName;
    private final BaseElement<?, ?> filteredRow;
    private final BaseElement<?, ?> noResultFilteredRow;

    public TableSearchFilterDemoPage(TestData<RemoteWebDriver> testData, SeleniumEasyTestDirector testDirector) {
        super(testDirector, testData);

        filterTable = new BaseElement<>(
            testData.getWebDriver(),
            testData.getWebDriver(),
            By.cssSelector(".filterable")
        );
        buttonFilter = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector(".btn-filter")
        );
        filterId = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector("[placeholder='#']")
        );
        filterUserName = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector("[placeholder='Username']")
        );
        filterFirstName = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector("[placeholder='First Name']")
        );
        filterLastName = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector("[placeholder='Last Name']")
        );
        filteredRow = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector("table tbody tr ")
        );
        noResultFilteredRow = new BaseElement<>(
                testData.getWebDriver(),
                filterTable,
                By.cssSelector("table tbody tr.no-result")
        );
    }

    @Step("Click on filter button")
    public TableSearchFilterDemoPage clickFilter() {
        buttonFilter
            .click();
        return this;
    }

    public TableSearchFilterDemoPage filterId(String number) {
        filterId.sendKeys(number);
        return this;
    }

    public TableSearchFilterDemoPage filterUserName(String userName) {
        filterUserName.sendKeys(userName);
        return this;
    }
    public TableSearchFilterDemoPage filterFirstName(String firstName) {
        filterFirstName.sendKeys(firstName);
        return this;
    }
    public TableSearchFilterDemoPage filterLastName(String lastName) {
        filterLastName.sendKeys(lastName);
        return this;
    }

    public TableSearchFilterDemoPage retrieveFilterTableSize(AtomicReference<Long> listSize) {
        long number = filteredRow.getDisplayedNumber();
        listSize.set(number);
        return this;
    }

    public TableSearchFilterDemoPage waitNoResultAbsent() {
        noResultFilteredRow.waitInvisible();
        return this;
    }

    public TableSearchFilterDemoPage waitNoResult() {
        noResultFilteredRow.waitVisible();
        return this;
    }
}
