package io.github.mkgerasimenko.model;

import io.github.mkgerasimenko.customannotation.Source;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for Lego processing.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Source(source = "lego.json")
public class Lego extends Product {

    private String ageRange;

    public Lego(final String name, final String generalCategory, final String subCategory) {
        super(name, generalCategory, subCategory);
    }
}
