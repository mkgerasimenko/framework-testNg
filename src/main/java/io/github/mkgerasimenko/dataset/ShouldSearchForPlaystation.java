package io.github.mkgerasimenko.dataset;

import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.Playstation;
import lombok.Data;

/**
 * A class for processing dates of Account and Playstation.
 */
@Data
public class ShouldSearchForPlaystation {

    private final Account account;
    private final Playstation playstation;
}
