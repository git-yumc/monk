package com.baizhi.monk.service;

import com.baizhi.monk.annotation.Log;
import com.baizhi.monk.dao.ArticleDao;
import com.baizhi.monk.dao.GuruDao;
import com.baizhi.monk.entity.Article;
import com.baizhi.monk.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yumc
 */
@Service
@Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;
    private final GuruDao guruDao;


    public ArticleServiceImpl(ArticleDao articleDao, GuruDao guruDao) {
        this.articleDao = articleDao;
        this.guruDao = guruDao;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>(16);
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds((page - 1) * rows, rows));
        for (Article article : articles) {
            Guru guru = new Guru();
            guru.setId(article.getGuruId());
            Guru guru1 = guruDao.selectByPrimaryKey(guru);
            article.setGuru(guru1);
        }
        int records = articleDao.selectCount(new Article());
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("rows", articles);
        map.put("page", page);
        map.put("records", records);
        map.put("total", total);
        return map;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public List<Article> select(String guruId) {
        Article article = new Article();
        article.setGuruId(guruId);
        return articleDao.select(article);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Article selectOne(String id) {
        Article article = new Article();
        article.setId(id);
        return articleDao.selectByPrimaryKey(article);
    }

    @Override
    @Log("添加")
    public void insert(Article article) {
        articleDao.insert(article);
    }

    @Override
    @Log("更新")
    public void update(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    @Log("删除")
    public void delete(Article article) {
        articleDao.deleteByPrimaryKey(article);
    }
}
