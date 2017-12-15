package io.github.mkgerasimenko.core;

import io.github.mkgerasimenko.wait.WaitCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static io.github.mkgerasimenko.utils.ElementTypeUtils.*;
import static io.github.mkgerasimenko.utils.RegexpUtils.getMappedElement;
import static io.github.mkgerasimenko.wait.WaitCondition.*;
import static io.github.sskorol.listeners.BaseListener.getDriverMetaData;
import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Parent class for all PageObjects. Defines common actions, like clicks, selections, etc.
 */
public abstract class BasePage implements Page {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriver mockDriver;
    private final WebElement mockElement;

    public BasePage() {
        this.driver = getDriverMetaData()._1;
        this.wait = getDriverMetaData()._2;
        this.mockDriver = mock(WebDriver.class);
        this.mockElement = mock(WebElement.class);
    }

    @Step("Navigate to {url}")
    public Page navigateTo(final String url) {
        driver.get(url);
        return this;
    }

    protected void click(final By locator) {
        click(locator, visible);
    }

    protected void click(final By locator, final WaitCondition condition) {
        elementOf(waitFor(locator, "", "", condition)).click();
    }

    protected void click(final By locator, final String value, final WaitCondition condition) {
        if (booleanOf(waitFor(locator, value, "", condition))) {
            click(locator);
        }
    }

    protected void phantomClick(final By locator) {
        when(mockDriver.findElement(locator)).thenReturn(mockElement);
        mockDriver.findElement(locator).click();
    }

    protected void type(final By locator, final CharSequence text) {
        type(locator, text, visible);
    }

    protected void type(final By locator, final CharSequence text, final WaitCondition condition) {
        elementOf(waitFor(locator, "", "", condition)).sendKeys(text);
    }

    protected void type(final By locator, final CharSequence text, final boolean shouldClear) {

        if (shouldClear) {
            clearInputField(locator);
        }
        type(locator, text);
    }

    protected void type(final By locator,
                        final CharSequence text,
                        final WaitCondition condition,
                        final String verifiedValue) {
        elementOf(waitFor(locator, verifiedValue, "", condition)).sendKeys(text);
    }

    protected void phantomType(final By locator, final CharSequence text) {
        when(mockDriver.findElement(locator)).thenReturn(mockElement);
        mockDriver.findElement(locator).sendKeys(text);
    }

    protected void clearInputField(final By locator) {
        click(locator, present);

        ofNullable(elementOf(waitFor(locator, "", "", present)))
                .filter(element -> !element.getAttribute("value").isEmpty())
                .ifPresent(WebElement::clear);
    }

    protected void select(final By locator, final String text) {
        new Select(waitFor(locator, "", "", visible)).selectByVisibleText(text);
    }

    protected void selectCategory(final String category) {
        Optional.of(By.linkText(category))
                .ifPresent(this::click);
    }

    public void selectByParameters(final By locator, final String value) {

        streamOf(waitFor(locator, "", "", allVisible))
                .filter(webElement -> webElement.getText().equals(value))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    protected void selectProduct(final By locator) {
        click(locator);
    }

    public void selectColor(final By locator, final String regexp, final String value) {
        getMappedElement(listOf(waitFor(locator, "", "", allVisible)),
                regexp, getHTMLofAccessCodePage(), value).click();
    }

    protected void selectByAttribute(final By locator, final String value) {
        streamOf(waitFor(locator, "", "", allVisible))
                .filter(webElement -> webElement.getAttribute("alt").equals(value))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    protected String getText(final By locator) {
        return elementOf(waitFor(locator, "", "", visible)).getText();
    }

    protected String getText(final By locator, final WaitCondition condition) {
        return elementOf(waitFor(locator, "", "", condition)).getText();
    }

    protected List<String> getTextNodes(final By locator) {
        return getTextNodes(locator, allPresent);
    }

    protected List<String> getTextNodes(final By locator, final WaitCondition condition) {
        return streamOf(waitFor(locator, "", "", condition))
                .map(WebElement::getText)
                .toList();
    }

    protected String getTextWithAttr(final By locator, final String attr, final WaitCondition condition) {
        return elementOf(waitFor(locator, "", "", condition)).getAttribute(attr);
    }

    protected String getTextByAttr(final By locator, final String attr, final String value) {

        return booleanOf(waitFor(locator, value, attr, attributeToBe))
                ? value
                : "";
    }

    protected String getPhantomText(final By locator, final String value) {
        when(mockElement.getText()).thenReturn(value);
        when(mockDriver.findElement(locator)).thenReturn(mockElement);
        return mockDriver.findElement(locator).getText();
    }

    private String getHTMLofAccessCodePage() {
        return driver.getPageSource();
    }

    @SuppressWarnings("unchecked")
    private <T, V, E, R> R waitFor(final T arg1, final V arg2, final E arg3, final WaitCondition condition) {
        return (R) wait.until((Function<WebDriver, ?>) condition.getType().apply(arg1, arg2, arg3));
    }
}
