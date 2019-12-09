package com.baizhi.monk.service;

import com.baizhi.monk.entity.Chapter;

import java.util.Map;

/**
 * @author yumc
 */
public interface ChapterService {

    Map<String, Object> selectAll(Integer page, Integer rows,String albumId);

    void insert(Chapter chapter);

    void update(Chapter chapter);

    void delete(Chapter chapter);
}
