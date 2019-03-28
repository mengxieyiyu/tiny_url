package com.tinyu.controller;

import com.tinyu.utils.CountSingletonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/count")
public class CountController {
    private static final Logger logger = LoggerFactory.getLogger(CountController.class);

    @Value("${message.txtFilePath}")
    private String txtFilePath;

    /**
     * 获取短连接访问次数
     *
     * @return
     */
    @RequestMapping(value = "/getAccessNum", method = RequestMethod.POST)
    public ResponseEntity<Object> count() {
        try {
            Map<String, Object> map = new HashMap<>();
            CountSingletonUtil singleton = CountSingletonUtil.getInstance();
            map.put("count", singleton.getAccessNums(txtFilePath));

            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("get access num error", HttpStatus.BAD_REQUEST);
        }
    }
}
