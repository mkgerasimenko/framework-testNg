package io.github.mkgerasimenko.testcases;

import io.github.mkgerasimenko.pages.SearchPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.github.mkgerasimenko.core.PageFactory.open;

/**
 * A simple class for Google page testing.
 */
public class AmazonTests {

    @Test(description = "Should search for keyword")
    @Feature("Search")
    @Story("Implement search functionality")
    @Issue("35")
    @TmsLink("35")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldSearchForKeyword() {
        open(SearchPage.class)
                .searchFor("Automation");
    }
}
