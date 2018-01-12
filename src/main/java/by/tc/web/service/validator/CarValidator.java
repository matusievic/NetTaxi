package by.tc.web.service.validator;

public final class CarValidator {
    private static final String NUMBER_REGEX = "\\dTAX\\d{4}";

    public static boolean isNumberValid(String number) {
        return number != null && number.matches(NUMBER_REGEX);
    }

    public static boolean isModelValid(String model) {
        return model != null;
    }
}
