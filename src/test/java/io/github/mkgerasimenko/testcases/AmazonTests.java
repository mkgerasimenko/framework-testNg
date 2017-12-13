package io.github.mkgerasimenko.testcases;

import io.github.mkgerasimenko.datasuppliers.Data;
import io.github.mkgerasimenko.datasuppliers.DataSuppliers;
import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.Parfume;
import io.github.mkgerasimenko.pages.LoginPage;
import io.github.mkgerasimenko.pages.ProductPage;
import io.github.mkgerasimenko.pages.SearchPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.github.mkgerasimenko.assertions.CustomAssertions.assertThat;
import static io.github.mkgerasimenko.core.PageFactory.at;
import static io.github.mkgerasimenko.core.PageFactory.open;

/**
 * A simple class for Google page testing.
 */
public class AmazonTests {

    @Data(source = "parfume.json", entity = Parfume.class)
    @Data(source = "accountAmazon.json", entity = Account.class)
    @Test(dataProvider = "getDataCollection", dataProviderClass = DataSuppliers.class)
    @Feature("Product search")
    @Story("Implement search functionality")
    @Issue("35")
    @TmsLink("35")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldSearchForParfume(final Parfume parfume, final Account account) {

        open(LoginPage.class)
                .login(account.getUsername(), account.getPassword());

        assertThat(account)
                .hasLoginStatus(at(LoginPage.class).getLoginStatus());

        at(SearchPage.class)
                .searchFor(parfume.getName());

        at(ProductPage.class)
                .selectCategoryBy(parfume.getSubCategory())
                .selectCheckboxBy(parfume.getSize())
                .selectProduct()
                .selectScent(parfume.getScent())
                .buy();

        assertThat(parfume)
                .hasPurchaseStatus(at(ProductPage.class).getPurchaseStatus());
    }
}
