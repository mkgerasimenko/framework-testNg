package io.github.mkgerasimenko.model;

import io.github.mkgerasimenko.customannotation.Source;
import lombok.Data;

/**
 * A simple model class for processing Account object.
 */
@Data
@Source(source = "accountAmazon.json")
public class Account {

    private final String username;
    private final String password;
}
