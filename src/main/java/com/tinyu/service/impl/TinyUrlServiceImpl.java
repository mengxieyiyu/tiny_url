package com.tinyu.service.impl;

import com.tinyu.dao.TinyUrlDao;
import com.tinyu.entity.TinyUrl;
import com.tinyu.service.TinyUrlService;
import com.tinyu.utils.GeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

/**
 * tiny url service
 */
@Service("tinyUrlService")
public class TinyUrlServiceImpl implements TinyUrlService {
    private static final Logger logger = LoggerFactory.getLogger(TinyUrlServiceImpl.class);

    @Autowired
    private TinyUrlDao tinyUrlDao;

    /**
     * get long url
     *
     * @param shortUrl
     * @return
     */
    public String getLongUrl(String shortUrl) {
        TinyUrl tinyUrl = tinyUrlDao.selectByShortUrl(shortUrl);
        if (null != tinyUrl && !StringUtils.isEmpty(tinyUrl.getLongUrl())) {
            return tinyUrl.getLongUrl();
        }

        return null;
    }

    /**
     * get short url
     *
     * @param longUrl
     * @return
     */
    public String getShortUrl(String longUrl) {
        try {
            TinyUrl tinyUrl = tinyUrlDao.selectByLongUrl(longUrl);
            if (tinyUrl != null && !StringUtils.isEmpty(tinyUrl.getShortUrl())) {
                return tinyUrl.getShortUrl();
            }

            // not exist, create new.
            String shortUrl = GeneratorUtil.shorten(longUrl);
            // save to db
            TinyUrl save = new TinyUrl();
            save.setLongUrl(longUrl);
            save.setShortUrl(shortUrl);
            save.setStatus(1);
            save.setCreateTime(new Date());
            save.setUpdateTime(new Date());

            tinyUrlDao.insertSelective(save);

            return shortUrl;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * define tiny url
     *
     * @param tinyUrlStr
     * @param longUrl
     * @return
     */
    public int saveTinyUrl(String tinyUrlStr, String longUrl) {
        TinyUrl tinyUrl = tinyUrlDao.selectByShortUrl(tinyUrlStr);
        if (null != tinyUrl) {
            return -1;
        }

        // save to db
        TinyUrl save = new TinyUrl();
        save.setCreateTime(new Date());
        save.setUpdateTime(new Date());
        save.setLongUrl(longUrl);
        save.setShortUrl(tinyUrlStr);
        save.setStatus(1);

        int result = tinyUrlDao.insertSelective(save);

        return result;
    }
}
