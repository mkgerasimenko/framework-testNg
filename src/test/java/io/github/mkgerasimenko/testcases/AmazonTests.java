package io.github.mkgerasimenko.testcases;

import io.github.mkgerasimenko.datasuppliers.Data;
import io.github.mkgerasimenko.datasuppliers.DataSuppliers;
import io.github.mkgerasimenko.model.Parfume;
import io.github.mkgerasimenko.pages.SearchPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.github.mkgerasimenko.core.PageFactory.open;

/**
 * A simple class for Google page testing.
 */
public class AmazonTests {

    @Data(source = "parfume.json", entity = Parfume.class)
    @Test(dataProvider = "getData", dataProviderClass = DataSuppliers.class)
    @Feature("Search")
    @Story("Implement search functionality")
    @Issue("35")
    @TmsLink("35")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldSearchForKeyword(final Parfume parfume) {
        open(SearchPage.class)
                .searchFor(parfume.getName() + " " + parfume.getAmount());
    }
}
