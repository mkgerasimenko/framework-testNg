package io.github.mkgerasimenko.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for Parfume processing.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Parfume extends Product {

    private String subCategory;
    private String amount;
    private String color;

    public Parfume(final String name, final String price, final String category) {
        super(name, price, category);
    }
}
