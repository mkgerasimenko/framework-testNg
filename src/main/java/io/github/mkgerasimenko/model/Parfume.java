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
    private String size;
    private String scent;

    public Parfume(final String name, final String generalCategory, final String purchaseStatus) {
        super(name, generalCategory, purchaseStatus);
    }
}
