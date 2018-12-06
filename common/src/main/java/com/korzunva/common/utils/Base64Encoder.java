package com.korzunva.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Base64Encoder - provides method to encrypt password
 */
public class Base64Encoder {

    private static final BASE64Encoder encoder = new BASE64Encoder();
    private static final BASE64Decoder decoder = new BASE64Decoder();

    /**
     * Encrypts string
     * @param str str to be encrypted
     * @return encrypted str
     */
    public static String encode(String str) {
        return encoder.encode(str.getBytes());
    }

    public static String decode(String str) {
        try {
            return new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            return "";
        }
    }

}
