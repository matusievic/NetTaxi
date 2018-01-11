package by.tc.web.service.encryptor;

import by.tc.web.service.encryptor.exception.EncryptorException;

public interface Encryptor {
    char[] encrypt(String source) throws EncryptorException;
}
