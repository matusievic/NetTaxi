package by.tc.web.service.encoder;

/**
 * This is an interface that defines Encoder methods
 */
public interface Encoder {
    /**
     * This method encodes the source string to the array of chars
     * @param source the source string
     * @return an encoded array of chars
     */
    char[] encode(String source);
}
