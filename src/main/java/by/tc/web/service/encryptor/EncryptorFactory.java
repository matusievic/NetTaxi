package by.tc.web.service.encryptor;

import by.tc.web.service.encryptor.impl.MD5Encryptor;

public final class EncryptorFactory {
    private static final EncryptorFactory instance = new EncryptorFactory();

    private final Encryptor encryptor = new MD5Encryptor();

    private EncryptorFactory() {}

    public static EncryptorFactory getInstance() {
        return instance;
    }

    public Encryptor createEncryptor() {
        return encryptor;
    }
}

