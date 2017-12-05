package io.github.mkgerasimenko.core;

/**
 * Key interface for PageObjects, which defines common actions.
 */
public interface Page {

    default Page navigateTo() {
        return navigateTo(url());
    }

    Page navigateTo(String url);

    default String url() {
        return BaseConfig.BASE_CONFIG.url();
    }
}
