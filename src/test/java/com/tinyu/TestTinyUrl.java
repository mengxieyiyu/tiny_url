package com.tinyu;

import com.tinyu.service.TinyUrlService;
import com.tinyu.utils.GeneratorUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTinyUrl extends BaseTest {

    @Autowired
    private TinyUrlService tinyUrlService;

    @Test
    public void getLongUrl() {
        String shortUrl = "http:localhost:8084/test/F3eaey";
        shortUrl = shortUrl.replace("http:localhost:8084/test/", "");
        String longUrl = tinyUrlService.getLongUrl(shortUrl);
        System.out.println("longUrl:" + longUrl);
    }

    @Test
    public void getShortUrl() {
        String longUrl = "https://music.163.com/#/discover/toplist?id=2250011882";
        String shortUrl = tinyUrlService.getShortUrl(longUrl);
        System.out.println("shortUrl:" + shortUrl);
    }

    @Test
    public void saveTinyUrl() {
        String key = "vqows09";
        String longUrl = "https://music.163.com/#/discover/playlist";
        int result = tinyUrlService.saveTinyUrl(key, longUrl);

        System.out.println("result: " + result);
    }

    @Test
    public void shortCreate() {
        try {
            String method = "shortenBySequence"; //shortenBySequence   md5
            String longUrl = "https://music.163.com/#/discover/playlist/fasdgao";
            String shortUrl = GeneratorUtil.autoCreateShorten(longUrl, method, 6);
            System.out.println(method + " shortUrl: " + shortUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
