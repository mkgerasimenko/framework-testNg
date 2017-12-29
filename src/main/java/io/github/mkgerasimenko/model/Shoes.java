package io.github.mkgerasimenko.model;

import io.github.mkgerasimenko.customannotation.Source;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for processing Shoes object.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Source(source = "shoes.json")
public class Shoes extends Product {

    private String size;
    private String color;
    private String brand;

    public Shoes(final String name, final String generalCategory, final String subCategory) {
        super(name, generalCategory, subCategory);
    }
}
