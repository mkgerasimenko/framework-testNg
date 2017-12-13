package io.github.mkgerasimenko.pages;

import io.github.mkgerasimenko.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static io.github.mkgerasimenko.core.BaseConfig.BASE_CONFIG;
import static io.github.mkgerasimenko.wait.WaitCondition.enabled;

@SuppressWarnings("JavadocType")
public class SearchPage extends BasePage {

    private final By inputSearch = By.id("twotabsearchtextbox");

    @Step("Search for \"{text}\".")
    public SearchPage searchFor(final String text) {
        type(inputSearch, text + Keys.ENTER, enabled);
        return this;
    }

    @Override
    public String url() {
        return BASE_CONFIG.url();
    }
}
