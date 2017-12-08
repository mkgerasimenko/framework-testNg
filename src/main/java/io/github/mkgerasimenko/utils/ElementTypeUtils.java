package io.github.mkgerasimenko.utils;

import lombok.experimental.UtilityClass;
import one.util.streamex.StreamEx;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * A wrapper for different elements' types casting.
 */
@SuppressWarnings({"HideUtilityClassConstructor", "PMD.UseUtilityClass"})
@UtilityClass
public final class ElementTypeUtils {

    public static <T> WebElement elementOf(final T element) {
        return (WebElement) element;
    }

    public static <T> boolean booleanOf(final T element) {
        return (Boolean) element;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<WebElement> listOf(final T element) {
        return (List<WebElement>) element;
    }

    public static <T> StreamEx<WebElement> streamOf(final T element) {
        return StreamEx.of(listOf(element));
    }
}
