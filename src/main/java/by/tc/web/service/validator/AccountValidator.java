package by.tc.web.service.validator;

import org.apache.log4j.Logger;

/**
 * This class contains validation method for account properties
 */
public final class AccountValidator {
    private static final Logger logger = Logger.getLogger(AccountValidator.class);
    private static final String PHONE_REGEX = "\\+?375[(29)(33)(44)](\\d{8})";
    private static final String NAME_REGEX = "[A-Z][a-z]+";
    private static final String SURNAME_REGEX = "[A-Z][a-z]+";
    private static final String PASSWORD_REGEX = "^(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    /**
     * This class validates a phone number
     * @param phone a string representation of a phone number
     * @return true - if the phone is valid, false - otherwise
     */
    public static boolean isPhoneValid(String phone) {
        return phone != null && phone.matches(PHONE_REGEX);
    }

    /**
     * This class validates a name
     * @param name a string representation of a name
     * @return true - if the name is valid, false - otherwise
     */
    public static boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    /**
     * This class validates a surname
     * @param surname a string representation of a surname
     * @return true - if the surname is valid, false - otherwise
     */
    public static boolean isSurnameValid(String surname) {
        return surname != null && surname.matches(SURNAME_REGEX);
    }

    /**
     * This class validates a password
     * @param password a string representation of a password
     * @return true - if the password is valid, false - otherwise
     */
    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    /**
     * This class validates a tariff
     * @param firstPassword a string representation of the first password
     * @param secondPassword a string representation of the second password
     * @return true - if the passwords are valid, false - otherwise
     */
    public static boolean isPasswordsValid(String firstPassword, String secondPassword) {
        return firstPassword != null && firstPassword.matches(PASSWORD_REGEX) && firstPassword.equals(secondPassword);
    }

    /**
     * This class validates a rating
     * @param rating a string representation of a rating
     * @return true - if the rating is valid, false - otherwise
     */
    public static boolean isRatingValid(String rating) {
        float parsedRating = -1;

        try {
            parsedRating = Float.parseFloat(rating);
        } catch (NumberFormatException e) {
            logger.info("Rating is incorrect -> parsing thrown an exception", e);
        }

        return parsedRating >= 0 && parsedRating <= 5;
    }

    /**
     * This class validates a tariff
     * @param tariff a string representation of a tariff
     * @return true - if the tariff is valid, false - otherwise
     */
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
