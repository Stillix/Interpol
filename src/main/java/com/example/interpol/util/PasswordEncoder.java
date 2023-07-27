package com.example.interpol.util;
import org.apache.commons.codec.digest.DigestUtils;
public class PasswordEncoder {

    public static String encode(String text){
        return DigestUtils.md5Hex(text);
    }
}
