package io.github.mkgerasimenko.pages;

import io.github.mkgerasimenko.core.BasePage;
import io.github.mkgerasimenko.wait.WaitCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static io.github.mkgerasimenko.core.BaseConfig.BASE_CONFIG;

@SuppressWarnings("JavadocType")
public class SearchPage extends BasePage {

    private final By inputSearch = By.id("twotabsearchtextbox");

    @Step("Search for \"{text}\".")
    public ProductPage searchFor(final String text) {
        type(inputSearch, text + Keys.ENTER, WaitCondition.enabled);
        return new ProductPage();
    }

    @Override
    public String url() {
        return BASE_CONFIG.url();
    }
}
