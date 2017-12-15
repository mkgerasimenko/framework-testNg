package io.github.mkgerasimenko.model;

import lombok.Data;

/**
 * A simple model class for processing Account object.
 */
@Data
public class Account {

    private final String username;
    private final String password;
    private final String loginStatus;
}
