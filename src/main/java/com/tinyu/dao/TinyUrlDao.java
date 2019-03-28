package com.tinyu.dao;

import com.tinyu.entity.TinyUrl;

import java.util.List;

public interface TinyUrlDao {
    /**
     * 根据主键获取信息
     * @param id
     * @return
     */
    TinyUrl selectByPrimaryKey(int id);

    /**
     * 根据短网址获取
     * @param shortUrl
     * @return
     */
    TinyUrl selectByShortUrl(String shortUrl);

    /**
     * 根据长网址获取
     * @param longUrl
     * @return
     */
    TinyUrl selectByLongUrl(String longUrl);

    /**
     * 插入
     * @param tinyUrl
     * @return
     */
    int insertSelective(TinyUrl tinyUrl);

    /**
     * 插入
     * @param tinyUrl
     * @return
     */
    int insert(TinyUrl tinyUrl);

    /**
     * 更新
     * @param tinyUrl
     * @return
     */
    int updateByPrimaryKeySelective(TinyUrl tinyUrl);

    /**
     * 获取可用短网址列表
     * @return
     */
    List<TinyUrl> selectByList();
}
