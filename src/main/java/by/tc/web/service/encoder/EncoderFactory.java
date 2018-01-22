package by.tc.web.service.encoder;

import by.tc.web.service.encoder.impl.MD5Encoder;

public final class EncoderFactory {
    private static final EncoderFactory instance = new EncoderFactory();

    private final Encoder encoder = new MD5Encoder();

    private EncoderFactory() {}

    public static EncoderFactory getInstance() {
        return instance;
    }

    public Encoder getEncoder() {
        return encoder;
    }
}

