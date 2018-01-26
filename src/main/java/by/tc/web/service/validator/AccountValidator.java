package by.tc.web.service.validator;

import org.apache.log4j.Logger;

public final class AccountValidator {
    private static final Logger logger = Logger.getLogger(AccountValidator.class);
    private static final String PHONE_REGEX = "\\+?375[(29)(33)(44)](\\d{8})";
    private static final String NAME_REGEX = "[A-Z][a-z]+";
    private static final String SURNAME_REGEX = "[A-Z][a-z]+";
    private static final String PASSWORD_REGEX = "^(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    public static boolean isPhoneValid(String phone) {
        return phone != null && phone.matches(PHONE_REGEX);
    }

    public static boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public static boolean isSurnameValid(String surname) {
        return surname != null && surname.matches(SURNAME_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isPasswordsValid(String firstPassword, String secondPassword) {
        return firstPassword != null && firstPassword.matches(PASSWORD_REGEX) && firstPassword.equals(secondPassword);
    }

    public static boolean isRatingValid(String rating) {
        float parsedRating = -1;

        try {
            parsedRating = Float.parseFloat(rating);
        } catch (NumberFormatException e) {
            logger.info("Rating is incorrect -> parsing thrown an exception", e);
        }

        return parsedRating >= 0 && parsedRating <= 5;
    }

    public static boolean isTariffValid(String tariff) {
        float parsedTariff = 0;

        try {
            parsedTariff = Float.parseFloat(tariff);
        } catch (NumberFormatException e) {
            logger.info("Tariff is incorrect -> parsing thrown an exception", e);
        }

        return parsedTariff > 0;
    }
}
