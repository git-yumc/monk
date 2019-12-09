package com.baizhi.monk.service;

import com.baizhi.monk.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Map<String, Object> selectAll(Integer page, Integer rows);

    List<Article> select(String guruId);

    Article selectOne(String id);

    void insert(Article article);

    void update(Article article);

    void delete(Article article);
}
