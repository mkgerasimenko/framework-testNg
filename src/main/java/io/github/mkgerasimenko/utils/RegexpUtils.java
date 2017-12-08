package io.github.mkgerasimenko.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.mkgerasimenko.core.BaseConfig.BASE_CONFIG;

/**
 * A utility class for different mapping the regexp value to origin value.
 */
@SuppressWarnings({"HideUtilityClassConstructor", "PMD.UseUtilityClass"})
@UtilityClass
public final class RegexpUtils {

    public static void clickOn(final List<WebElement> webElements,
                               final String regexp,
                               final String accessCodeHtml,
                               final String value) {

        final Map<String, WebElement> hashMap = new LinkedHashMap<>();

        final List<String> arrayList = new ArrayList<>();

        final Pattern p = Pattern.compile(regexp);
        final Matcher m = p.matcher(accessCodeHtml);

        while (m.find()) {
            arrayList.add(m.group(BASE_CONFIG.regexpGroup()));
        }

        for (int i = 0; i < arrayList.size(); i++) {
            hashMap.put(arrayList.get(i), webElements.get(i));
        }

        hashMap.get(value).click();
    }
}
