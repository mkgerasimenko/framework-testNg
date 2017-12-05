package io.github.mkgerasimenko.model;

import lombok.Getter;

/**
 * A simple base class for processing common methods for all children classes.
 */
@Getter
public abstract class Product {

    private final String name;
    private final String price;

    public Product(final String name, final String price) {
        this.name = name;
        this.price = price;
    }
}
