package io.github.mkgerasimenko.dataset;

import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.Parfume;
import lombok.Data;

/**
 * A class for processing dates of Account and Parfume.
 */
@Data
public class ShouldSearchForParfume {

    private final Account account;
    private final Parfume parfume;
}
