package by.tc.web.service.converter;

import org.apache.log4j.Logger;

import java.util.Optional;

public final class Converter {
    private static final Logger logger = Logger.getLogger(Converter.class);

    public static Optional<Integer> parseInt(String value) {
        Optional<Integer> result = Optional.empty();
        try {
            result = Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            logger.error("Cannot convert string to integer -> incorrect number was provided", e);
        }

        return result;
    }

    public static Optional<Float> parseFloat(String value) {
        Optional<Float> result = Optional.empty();
        try {
            return Optional.of(Float.parseFloat(value));
        } catch (NumberFormatException e) {
            logger.error("Cannot convert string to float -> incorrect number was provided", e);
        }
        return result;
    }
}
