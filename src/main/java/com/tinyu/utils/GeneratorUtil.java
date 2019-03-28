package com.tinyu.utils;


import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

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
     * @param length  length
     * @return
     */
    public static String shortenByMd5(String longUrl, int length) throws Exception {
        // md5 salt
        String key = "sign01";

        // encode by md5
        String md5Result = MD5Util.encodeMD5Hex(key + longUrl);
        String hex = md5Result;

        String resUrl = "";

        // substring 8 bit
        String subStr = hex.substring(0, 8);

        long hexLong = 0x3FFFFFFF & Long.parseLong(subStr, 16);
        for (int j = 0; j < length; j++) {
            long index = 0x0000003D & hexLong;
            resUrl += DIGITS[(int) index];
            hexLong = hexLong >> 30 / length;
        }

        return resUrl;
    }

    /**
     * @param longUrl
     * @return
     */
    protected static String shortenBySequence(String longUrl) {
        synchronized(GeneratorUtil.class) {
            Calendar ca = Calendar.getInstance();
            long myseq = ca.getTimeInMillis();
            String shortUrl = to62RadixString(myseq);
            return shortUrl;
        }
    }

    /**
     * @param seq
     * @return
     */
    private static String to62RadixString(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            int remainder = (int) (seq % 62);
            sBuilder.append(DIGITS[remainder]);
            seq = seq / 62;
            if (seq == 0) {
                break;
            }
        }
        return sBuilder.toString();
    }

    /**
     * @param longurl
     * @param method
     * @return
     */
    public static String autoCreateShorten(String longurl, String method, int length) throws Exception {
        switch (method) {
            case "shortenBySequence":
                return shortenBySequence(longurl);
            default:
                return shortenByMd5(longurl, length);
        }
    }
}
