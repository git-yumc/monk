package com.baizhi.monk.service;

import com.baizhi.monk.entity.Banner;

import java.util.List;
import java.util.Map;

/**
 * @author yumc
 */
public interface BannerService {
    /**
     * 分页查询所有
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> selectAll(Integer page, Integer rows);

    /**
     * 添加
     * @param banner
     */
    List<Banner> select();
    void insert(Banner banner);

    /**
     * 更新
     * @param banner
     */
    void update(Banner banner);

    /**
     * 删除
     * @param banner
     */
    void delete(Banner banner);

    void deleteList(List<String> list);


}
