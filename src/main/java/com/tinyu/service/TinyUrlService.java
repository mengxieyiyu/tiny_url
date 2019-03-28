package com.tinyu.service;

public interface TinyUrlService {
    /**
     * 获取长网址
     * @param shortUrl
     * @return
     */
    String getLongUrl(String shortUrl);

    /**
     * 获取短网址
     * @param longUrl
     * @return
     */
    String getShortUrl(String longUrl);

    /**
     * 自定义锻炼
     * @return
     */
    int saveTinyUrl(String tinyUrl,String longUrl);
}
