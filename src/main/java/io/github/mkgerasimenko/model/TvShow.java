package io.github.mkgerasimenko.model;

import io.github.mkgerasimenko.customannotation.Source;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple model class for processing Tv Show object.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Source(source = "tvShow.json")
public class TvShow extends Product {

    private String year;

    public TvShow(final String name, final String generalCategory, final String subCategory) {
        super(name, generalCategory, subCategory);
    }
}
