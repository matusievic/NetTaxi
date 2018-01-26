package by.tc.web.service.validator;

/**
 * This class contains methods for car validation
 */
public final class CarValidator {
    private static final String NUMBER_REGEX = "\\dTAX\\d{4}";

    /**
     * This class validates a car number
     * @param number a string representation of a car number
     * @return true - if the car number is valid, false - otherwise
     */
    public static boolean isNumberValid(String number) {
        return number != null && number.matches(NUMBER_REGEX);
    }

    /**
     * This class validates a car model
     * @param model a string representation of a car model
     * @return true - if the car model is valid, false - otherwise
     */
    public static boolean isModelValid(String model) {
        return model != null;
    }
}
