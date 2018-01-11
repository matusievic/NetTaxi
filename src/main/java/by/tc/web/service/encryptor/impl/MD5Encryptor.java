package by.tc.web.service.encryptor.impl;

import by.tc.web.service.encryptor.Encryptor;
import by.tc.web.service.encryptor.exception.EncryptorException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor implements Encryptor {
    @Override
    public char[] encrypt(String source) throws EncryptorException {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptorException(e);
        }
        return result.toCharArray();
    }
}
