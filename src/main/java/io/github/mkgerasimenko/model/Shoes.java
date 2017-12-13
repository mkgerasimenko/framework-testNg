package io.github.mkgerasimenko.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for Parfume processing.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Shoes extends Product {

    private String subCategory;
    private String type;
    private String size;
    private String color;

    public Shoes(String name, String generalCategory, String purchaseStatus) {
        super(name, generalCategory, purchaseStatus);
    }
}
