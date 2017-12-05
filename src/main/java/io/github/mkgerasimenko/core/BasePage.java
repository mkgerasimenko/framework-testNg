package io.github.mkgerasimenko.core;

import io.github.mkgerasimenko.utils.ElementTypeUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

import static io.github.sskorol.listeners.BaseListener.getDriverMetaData;

/**
 * Parent class for all PageObjects. Defines common actions, like clicks, selections, etc.
 */
public abstract class BasePage implements Page {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage() {
        this.driver = getDriverMetaData()._1;
        this.wait = getDriverMetaData()._2;
    }

    @Step("Navigate to {url}")
    public Page navigateTo(final String url) {
        driver.get(url);
        return this;
    }

    protected void type(final By locator, final CharSequence text, final WaitCondition condition) {
        ElementTypeUtils.elementOf(waitFor(locator, "", condition)).sendKeys(text);
    }

    protected void type(final By locator, final CharSequence text) {
        type(locator, text, WaitCondition.visible);
    }

    protected void select(final By locator, final String text) {
        new Select(waitFor(locator, "", WaitCondition.visible)).selectByVisibleText(text);
    }

    protected List<String> getTextNodes(final By locator) {
        return getTextNodes(locator, WaitCondition.allPresent);
    }

    protected List<String> getTextNodes(final By locator, final WaitCondition condition) {
        return ElementTypeUtils.streamOf(waitFor(locator, "", condition))
                .map(WebElement::getText)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private <T, V, R> R waitFor(final T arg1, final V arg2, final WaitCondition condition) {
        return (R) wait.until((Function<WebDriver, ?>) condition.getType().apply(arg1, arg2));
    }
}
