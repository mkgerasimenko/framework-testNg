package io.github.mkgerasimenko.dataset;

import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.Shoes;
import lombok.Data;

/**
 * A class for processing dates of Account and Shoes.
 */
@Data
public class ShouldSearchForShoes {

    private final Account account;
    private final Shoes shoes;
}
