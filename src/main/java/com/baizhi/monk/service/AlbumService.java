package com.baizhi.monk.service;

import com.baizhi.monk.entity.Album;

import java.util.List;
import java.util.Map;

/**
 * @author yumc
 */
public interface AlbumService {
    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> selectAll(Integer page, Integer rows);

    Album selectOne(String id);

    void insert(Album album);

    void update(Album album);

    void delete(Album album);

    void deleteList(List<String> list);

}
