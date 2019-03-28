package com.tinyu.controller;

import com.tinyu.service.TinyUrlService;
import com.tinyu.utils.CountSingletonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@ApiIgnore
@Controller
@RequestMapping("/view")
public class ViewController {
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    /**
     * access num
     */
    @Value("${message.txtFilePath}")
    private String txtFilePath;

    @Resource
    private TinyUrlService tinyUrlService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * access tiny url
     *
     * @param tinyUrl
     * @return
     */
    @RequestMapping("/{tinyUrl}")
    public String webJump(@PathVariable String tinyUrl) {
        try {
            String longUrl = tinyUrlService.getLongUrl(tinyUrl);
            // count access
            CountSingletonUtil singleton = CountSingletonUtil.getInstance();
            singleton.GetVisitCount(txtFilePath);

            return "redirect:" + longUrl;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "error";
        }
    }
}
