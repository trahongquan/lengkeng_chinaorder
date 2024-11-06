package com.shoponline.chinaorder.support;

import org.springframework.util.Base64Utils;


public class PathEncoderDecoder {
    public static String encodePath(String path) {
        return Base64Utils.encodeToString(path.getBytes());
    }

    public static String decodePath(String base64Encoded) {
        return  new String(Base64Utils.decodeFromString(base64Encoded));
    }
}
