package io.github.mkgerasimenko.testcases;

import io.github.mkgerasimenko.pages.SearchPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.github.mkgerasimenko.core.PageFactory.at;
import static io.github.mkgerasimenko.core.PageFactory.open;
import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(at(SearchPage.class).getLinksAmount()).isGreaterThan(8);
    }
}
