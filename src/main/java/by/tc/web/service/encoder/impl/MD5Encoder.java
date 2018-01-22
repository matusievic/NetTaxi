package by.tc.web.service.encoder.impl;

import by.tc.web.service.encoder.Encoder;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder implements Encoder {
    private static final Logger logger = Logger.getLogger(MD5Encoder.class);

    @Override
    public char[] encode(String source) {
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
            logger.error("Cannot encode source string -> MD5 algorithm not found", e);
            return null;
        }
        return result.toCharArray();
    }
}
