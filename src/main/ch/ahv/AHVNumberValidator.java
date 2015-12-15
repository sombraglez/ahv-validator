package ch.ahv;

import java.util.regex.Pattern;

import static ch.ahv.StringUtils.isNullOrEmpty;

public class AHVNumberValidator {

    public static final int AHV_NUMBER_LENGTH_WITHOUT_SEPARATORS = 13;

    public static final String AHV_SEPARATOR = ".";

    public static final int FACTOR_3 = 3;

    public static final int FACTOR_1 = 1;

    public static boolean isValidAHVNumber(String ahvToValidate) {
        if (isNullOrEmpty(ahvToValidate)) {
            return false;
        }

        if (ahvToValidate.contains(AHV_SEPARATOR)) {
            return isValidFormattedAHVNumber(ahvToValidate);
        }
        return isValidUnformattedAHVNumber(ahvToValidate);
    }

    static boolean isValidFormattedAHVNumber(String ahvToValidate) {
        return hasAHVNumberFormat(ahvToValidate) && isControlDigitCorrect(ahvToValidate);
    }

    static boolean isValidUnformattedAHVNumber(String ahvToValidate) {
        boolean containsOnlyNumbers = containsOnlyNumbersWithLength(ahvToValidate, AHV_NUMBER_LENGTH_WITHOUT_SEPARATORS);
        if (containsOnlyNumbers) {
            String formattedAHVNumber = getFormattedAHVNumber(ahvToValidate);
            return isControlDigitCorrect(formattedAHVNumber);
        }
        return false;
    }

    static String getUnformattedAHVNumber(String str) {
        return str.replaceAll("[^0-9]", "");
    }

    static int getControlDigit(String ahvNumber) {
        return Character.getNumericValue(ahvNumber.charAt(ahvNumber.length() - 1));
    }

    static String getFormattedAHVNumber(String unformattedAhvNumber) {

        String ahv1 = unformattedAhvNumber.substring(0, 3);
        String ahv2 = unformattedAhvNumber.substring(3, 7);
        String ahv3 = unformattedAhvNumber.substring(7, 11);
        String ahv4 = unformattedAhvNumber.substring(11);

        StringBuffer formattedAhvNumber = new StringBuffer(ahv1).append(AHV_SEPARATOR)
                .append(ahv2).append(AHV_SEPARATOR)
                .append(ahv3).append(AHV_SEPARATOR)
                .append(ahv4);
        return formattedAhvNumber.toString();
    }

    static boolean isControlDigitCorrect(String ahvNumber) {

        String unformattedAHVNumber = getUnformattedAHVNumber(ahvNumber);
        int controlDigit = getControlDigit(ahvNumber);
        int factor = FACTOR_3;
        int total = 0;
        int nextToLastIndex = AHV_NUMBER_LENGTH_WITHOUT_SEPARATORS - 2;

        for (int i = nextToLastIndex; i >= 0; i--) {
            int value = Character.getNumericValue(unformattedAHVNumber.charAt(i));
            total += (value * factor);
            if (factor == FACTOR_3) {
                factor = FACTOR_1;
            } else {
                factor = FACTOR_3;
            }
        }

        Double nextMultipleOfTen = Math.ceil((double) total / 10);
        int expectedControlDigit = (nextMultipleOfTen.intValue() * 10) - total;

        return controlDigit == expectedControlDigit;
    }

    static boolean hasAHVNumberFormat(String ahvNumber) {
        Pattern ahvNumberPattern = Pattern.compile("[0-9]{3}\\.[0-9]{4}\\.[0-9]{4}\\.[0-9]{2}");
        return ahvNumberPattern.matcher(ahvNumber).matches();
    }

    static boolean containsOnlyNumbersWithLength(String value, int length) {
        StringBuffer sb = new StringBuffer().append("[0-9]{").append(length).append("}");
        Pattern numbersPattern = Pattern.compile(sb.toString());
        return numbersPattern.matcher(value).matches();
    }

}
