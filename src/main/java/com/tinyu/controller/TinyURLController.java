package com.tinyu.controller;

import com.tinyu.vo.ResultVo;
import com.tinyu.service.TinyUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tinyurl")
public class TinyURLController {
    private static final Logger logger = LoggerFactory.getLogger(TinyURLController.class);

    @Resource
    private TinyUrlService tinyUrlService;

    @Value("${message.domain}")
    private String domainName;

    /**
     * get tiny url
     *
     * @param longUrl
     * @return
     */
    @RequestMapping(value = "/getTinyUrl", method = RequestMethod.POST)
    public ResponseEntity<Object> getTinyUrl(@RequestParam(value = "longUrl") String longUrl) {
        try {
            String shortUrl = tinyUrlService.getShortUrl(longUrl);
            if (StringUtils.isEmpty(shortUrl))
                return new ResponseEntity<>("create tiny url error", HttpStatus.BAD_REQUEST);

            ResultVo resultVo = new ResultVo();
            resultVo.setLongUrl(longUrl);
            resultVo.setShortUrl(domainName + shortUrl);

            return new ResponseEntity<>(resultVo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("get tiny url error", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get Original URL
     *
     * @param shortUrl
     * @return
     */
    @RequestMapping(value = "/getOriginalUrl", method = RequestMethod.POST)
    public ResponseEntity<Object> getLongUrl(@RequestParam(value = "shortUrl") String shortUrl) {
        try {
            // convert full url to tiny url.
            String tinyUrl = shortUrl.replace(domainName, "");
            String longUrl = tinyUrlService.getLongUrl(tinyUrl);
            if (StringUtils.isEmpty(longUrl))
                return new ResponseEntity<>("the tiny url is wrong", HttpStatus.BAD_REQUEST);

            ResultVo resultVo = new ResultVo();
            resultVo.setLongUrl(longUrl);
            resultVo.setShortUrl(shortUrl);

            return new ResponseEntity<>(resultVo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("get long url error", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * define tinyUrl
     *
     * @param key
     * @param longUrl
     * @return
     */
    @RequestMapping(value = "/setTinyUrl", method = RequestMethod.POST)
    public ResponseEntity<Object> setTinyUrl(@RequestParam(value = "key") String key, @RequestParam(value = "longUrl") String longUrl) {
        try {
            int result = tinyUrlService.saveTinyUrl(key, longUrl);
            if (result == -1)
                return new ResponseEntity<>("this tiny url already exists", HttpStatus.BAD_REQUEST);

            ResultVo resultVo = new ResultVo();
            resultVo.setLongUrl(longUrl);
            resultVo.setShortUrl(domainName + key);

            return new ResponseEntity<>(resultVo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("setting tiny url error", HttpStatus.BAD_REQUEST);
        }
    }
}
