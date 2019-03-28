package com.tinyu.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResultVo implements Serializable {
    private String shortUrl;
    private String longUrl;
}
