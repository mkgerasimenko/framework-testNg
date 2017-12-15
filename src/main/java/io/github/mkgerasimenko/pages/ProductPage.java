package io.github.mkgerasimenko.pages;

import io.github.mkgerasimenko.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static io.github.mkgerasimenko.core.BaseConfig.BASE_CONFIG;
import static io.github.mkgerasimenko.utils.ConvertUnitUtils.convert;

@SuppressWarnings("JavadocType")
public class ProductPage extends BasePage {

    private static final String REGEXP_FOR_COLOR = ".colorsprite.*(\\n*\\t*.*).a-size-small a-color-base.>(.*?)<";
    private static final String PURCHASE_STATUS = "Operation was successfully completed";
    private final By checkboxes = By.cssSelector(".a-label.a-checkbox-label");
    private final By resultProducts = By.xpath("//ul[@id='s-results-list-atf']/li");
    private final By colors = By.cssSelector(".colorsprite");
    private final By scents = By.xpath("(//img[@id=''])");
    private final By getAllScentsButton = By.id("expanderButton_scent_name");
    private final By buyButton = By.id("buy");
    private final By sizes = By.cssSelector(".buttonsprite");

    public ProductPage selectByColor(final String color) {
        selectColor(colors, REGEXP_FOR_COLOR, color);
        return this;
    }

    @Step("Select the following category \"{category}\".")
    public ProductPage selectCategoryBy(final String category) {
        selectCategory(category);
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

    public ProductPage selectProduct() {
        selectProduct(resultProducts);
        return this;
    }

    @Step("Select the following scent \"{value}\".")
    public ProductPage selectScent(final String value) {
        click(getAllScentsButton);
        selectByAttribute(scents, value);
        return this;
    }

    public ProductPage buy() {
        phantomClick(buyButton);
        return this;
    }

    public String getPurchaseStatus() {
        return getPhantomText(buyButton, PURCHASE_STATUS);
    }

    @Override
    public String url() {
        return BASE_CONFIG.url();
    }
}
