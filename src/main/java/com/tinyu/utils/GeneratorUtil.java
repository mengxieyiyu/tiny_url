package com.tinyu.utils;

/**
 * the generator of short url .
 */
public class GeneratorUtil {
    static final char[] DIGITS = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * get shorten url
     *
     * @param longUrl longUrl
     * @return
     */
    public static String shorten(String longUrl) throws Exception {
        // md5 salt
        String key = "sign01";

        // encode by md5
        String md5Result = MD5Util.encodeMD5Hex(key + longUrl);
        String hex = md5Result;

        String resUrl = "";

        // substring 8 bit
        String subStr = hex.substring(0, 8);

        long hexLong = 0x3FFFFFFF & Long.parseLong(subStr, 16);
        for (int j = 0; j < 6; j++) {
            long index = 0x0000003D & hexLong;
            resUrl += DIGITS[(int) index];
            hexLong = hexLong >> 5;
        }

        return resUrl;
    }
}
