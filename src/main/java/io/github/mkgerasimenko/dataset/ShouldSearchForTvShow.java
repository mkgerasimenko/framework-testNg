package io.github.mkgerasimenko.dataset;

import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.TvShow;
import lombok.Data;

/**
 * A class for processing dates of Account and TvShow.
 */
@Data
public class ShouldSearchForTvShow {

    private final Account account;
    private final TvShow tvShow;
}
