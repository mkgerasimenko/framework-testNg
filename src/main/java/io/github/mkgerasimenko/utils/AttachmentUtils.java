package io.github.mkgerasimenko.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.experimental.UtilityClass;

/**
 * Attachments processing class. Use it along with Allure annotations.
 */
@SuppressWarnings({"HideUtilityClassConstructor", "PMD.UseUtilityClass"})
@UtilityClass
public class AttachmentUtils {

    public static void attachUri(final String name, final String data) {
        Allure.addAttachment(name, "text/uri-list", data);
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachLog(final String name, final String data) {
        return data;
    }
}
