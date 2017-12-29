package io.github.mkgerasimenko.dataset;

import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.Lego;
import lombok.Data;

/**
 * A class for processing dates of Account and Lego.
 * Note: If you want to use more Objects in Test -
 * should add new Object as a new line
 */
@Data
public class ShouldSearchForLego {

    private final Account account;
    private final Lego lego;
}
