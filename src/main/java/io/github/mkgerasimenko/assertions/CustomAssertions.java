package io.github.mkgerasimenko.assertions;

import io.github.mkgerasimenko.model.Account;
import io.github.mkgerasimenko.model.Product;

/**
 * A simple base class for Assertions.
 */
public final class CustomAssertions {

    private CustomAssertions() {
        throw new UnsupportedOperationException("Illegal access to private constructor.");
    }

    public static LoginPageAssert assertThat(final Account account) {
        return new ModelSoftAssert().assertThat(LoginPageAssert.class, Account.class, account);
    }

    public static ProductPageAssert assertThat(final Product product) {
        return new ModelSoftAssert().assertThat(ProductPageAssert.class, Product.class, product);
    }
}
