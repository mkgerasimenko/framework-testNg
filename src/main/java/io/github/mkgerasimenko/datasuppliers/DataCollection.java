package io.github.mkgerasimenko.datasuppliers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Data annotation for marking test method for a collection of annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataCollection {

    Data[] value();
}
