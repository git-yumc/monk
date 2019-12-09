package com.baizhi.monk.service;

import com.baizhi.monk.entity.Guru;

import java.util.Map;

public interface GuruService {
    Map<String, Object> selectAll(Integer page, Integer rows);

    void insert(Guru guru);

    void update(Guru guru);

    void delete(Guru guru);
}
