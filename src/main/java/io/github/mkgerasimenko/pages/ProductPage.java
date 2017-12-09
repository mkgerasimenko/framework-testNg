package io.github.mkgerasimenko.pages;

import io.github.mkgerasimenko.core.BasePage;
import org.openqa.selenium.By;

import static io.github.mkgerasimenko.core.BaseConfig.BASE_CONFIG;
import static io.github.mkgerasimenko.utils.ConvertUnitUtils.convert;

@SuppressWarnings("JavadocType")
public class ProductPage extends BasePage {

    private static final String REGEXP_FOR_COLOR = ".colorsprite.*(\\n*\\t*.*).a-size-small a-color-base.>(.*?)<";
    private final By checkboxes = By.xpath("//li/span/span/div/label");
    private final By resultProducts = By.xpath("//ul[@id='s-results-list-atf']/li");
    private final By colors = By.cssSelector(".colorsprite");
    private final By sizes = By.cssSelector(".buttonsprite");

    public ProductPage selectByColor(final String color) {
        selectColor(colors, REGEXP_FOR_COLOR, color);
        return this;
    }

    public ProductPage selectBy(final String condition) {
        selectByParameters(sizes, convert(condition));
        return this;
    }

    public ProductPage selectCheckboxBy(final String condition) {
        selectByParameters(checkboxes, condition);
        return this;
    }

    public ProductPage selectProduct(final String condition) {
        selectProductBy(resultProducts, condition);
        return this;
    }

    @Override
    public String url() {
        return BASE_CONFIG.url();
    }
}
