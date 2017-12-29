package io.github.mkgerasimenko.model;

import io.github.mkgerasimenko.customannotation.Source;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for processing Playstation object.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Source(source = "playstation.json")
public class Playstation extends Product {

    public Playstation(final String name, final String generalCategory, final String subCategory) {
        super(name, generalCategory, subCategory);
    }
}
