package by.tc.web.service.converter;

import java.util.Optional;

public final class Converter {
    public static Optional<Integer> parseInt(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Float> parseFloat(String value) {
        try {
            return Optional.of(Float.parseFloat(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
