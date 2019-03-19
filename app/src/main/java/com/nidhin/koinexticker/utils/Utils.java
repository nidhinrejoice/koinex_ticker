package com.nidhin.koinexticker.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;

public class Utils {

    public static boolean isNotStringEmpty(String string) {
        return string != null ? string.length() != 0 : false;
    }

    public static boolean isNotListEmpty(List list) {
        return list != null ? (list.isEmpty() ? false : true) : false;
    }

    public static boolean isNotNullOrZero(Double value) {
        return value != null ? value > 0 : false;
    }


    public static String makeFirstLetterUpperCase(String paramString) {
        if (isNotStringEmpty(paramString)) {
            paramString = paramString.toLowerCase();
            String a = paramString.substring(0, 1).toUpperCase();
            paramString = a + paramString.substring(1, paramString.length());
        }
        return removeUnderScores(paramString);
    }

    public static String removeUnderScores(String paramString) {
        if ((paramString != null) && (!paramString.isEmpty())) {
            paramString = paramString.replaceAll("_", " ");
        }
        return paramString;
    }

    public static String formatStringToDisplay(String string) {
        string = removeUnderScores(string);
        string = makeFirstLetterUpperCase(string);
        return string;
    }

    public static String twoDecimalPlaces(Double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);
        return df.format(value);
    }


    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,##,##,###.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMaximumFractionDigits(0);

        return "\u20B9" + df.format(amount);
    }


}
