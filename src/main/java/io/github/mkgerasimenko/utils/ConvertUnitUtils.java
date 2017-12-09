package io.github.mkgerasimenko.utils;

import lombok.experimental.UtilityClass;

import static io.github.mkgerasimenko.utils.ConvertUnitUtils.Units.PARFUM_UNITS;
import static io.github.mkgerasimenko.utils.ConvertUnitUtils.Units.SHOES_UNITS;

/**
 * A utility class for converting size.
 */
@SuppressWarnings({"HideUtilityClassConstructor", "PMD.UseUtilityClass", "MultipleStringLiterals"})
@UtilityClass
public final class ConvertUnitUtils {

    /**
     * An enum for processing some units.
     */
    public enum Units {

        SHOES_UNITS((Object[]) new String[][]{
                {"7.5", "8", "8.5", "9", "9.5", "10"},
                {"40", "40.5", "41", "41.5", "42", "43"},
                {"7", "7.5", "8", "8.5", "9", "9.5"}}),
        PARFUM_UNITS((Object[]) new String[][]{
                {"1", "1.5", "1.7", "2.0", "2.5", "3.4"},
                {"30", "45", "50", "60", "75", "100"}});
        private String[][] mass;

        Units(final Object... args) {
            this.mass = (String[][]) args;
        }

        public String[][] getMass() {
            return mass.clone();
        }
    }

    public static String convert(final String value) {

        String res = "";

        if (value.contains("UA")) {
            res = getCorrectUnit(value.replace("UA", ""), SHOES_UNITS.getMass(), 1);
        } else if (value.contains("UK")) {
            res = getCorrectUnit(value.replace("UK", ""), SHOES_UNITS.getMass(), 2);
        } else if (value.contains("ml")) {
            res = getCorrectUnit(value.replace("ml", ""), PARFUM_UNITS.getMass(), 1);
        }

        return res;
    }

    private String getCorrectUnit(final String value, final String[][] myArr, final int attr) {
        String res = "";

        for (int i = 0; i < myArr.length; i++) {
            for (int j = 0; j < myArr[i].length; j++) {
                if (myArr[i][j].equals(value)) {
                    res = myArr[attr - i][j];
                }
            }
        }
        return res;
    }
}
