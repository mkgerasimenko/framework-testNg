package io.github.mkgerasimenko.testcases;

import io.github.mkgerasimenko.datasuppliers.Data;
import io.github.mkgerasimenko.datasuppliers.DataSuppliers;
import io.github.mkgerasimenko.model.Parfume;
import io.github.mkgerasimenko.model.Shoes;
import io.github.mkgerasimenko.pages.ProductPage;
import io.github.mkgerasimenko.pages.SearchPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.github.mkgerasimenko.core.PageFactory.at;
import static io.github.mkgerasimenko.core.PageFactory.open;

/**
 * A simple class for Google page testing.
 */
public class AmazonTests {

    @Data(source = "parfume.json", entity = Parfume.class)
    @Test(dataProvider = "getData", dataProviderClass = DataSuppliers.class)
    @Feature("Product search")
    @Story("Implement search functionality")
    @Issue("35")
    @TmsLink("35")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldSearchForKeyword(final Parfume parfume) {

        open(SearchPage.class)
                .searchFor(parfume.getName())
                .selectCategoryBy(parfume.getSubCategory());

        at(ProductPage.class)
                .selectCheckboxBy(parfume.getAmount())
                .selectProduct(parfume.getPrice());
    }

    @Data(source = "shoes.json", entity = Shoes.class)
    @Test(dataProvider = "getData", dataProviderClass = DataSuppliers.class)
    @Feature("Product search")
    @Story("Implement search functionality")
    @Issue("36")
    @TmsLink("36")
    @Severity(SeverityLevel.MINOR)
    public void shouldSearchForShoes(final Shoes shoes) {

        open(SearchPage.class)
                .searchFor(shoes.getName())
                .selectCategoryBy(shoes.getSubCategory())
                .selectCategoryBy(shoes.getType());

        at(ProductPage.class)
                .selectByColor(shoes.getColor())
                .selectBy(shoes.getSize());
    }
}
