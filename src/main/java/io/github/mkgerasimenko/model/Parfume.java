package io.github.mkgerasimenko.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for Parfume processing.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Parfume extends Product {

    private String amount;

    public Parfume(final String name, final String price) {
        super(name, price);
    }
}
