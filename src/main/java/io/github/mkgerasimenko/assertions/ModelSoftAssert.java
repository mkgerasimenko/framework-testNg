package io.github.mkgerasimenko.assertions;

import static io.github.mkgerasimenko.listeners.SoftAssertListener.getSoftAssert;

/**
 * A helper class for soft assert processing to any model.
 */
public class ModelSoftAssert {

    public <T, V> V assertThat(final Class<V> assertClass, final Class<T> actualClass, final T actual) {
        return getSoftAssert().proxy(assertClass, actualClass, actual);
    }
}
