package com.baizhi.monk.controller;

import com.baizhi.monk.entity.Article;
import com.baizhi.monk.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yumcw
 * @date 2019-12-03
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("select")
    public Map<String, Object> select(Integer page, Integer rows) {
        Map<String, Object> map = articleService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(Article article, String oper) {
        Map<String, Object> map = new HashMap<>(16);
        if (oper.equals("add")) {
            article.setId(null);
            articleService.insert(article);
            map.put("id", article.getId());
        } else if (oper.equals("edit")) {
            articleService.update(article);
            map.put("id", article.getId());
        } else if (oper.equals("del")) {
            articleService.delete(article);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile img, String id, HttpSession session, HttpServletRequest request) {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/statics/img/article");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 防止重名操作
        String originalFilename = img.getOriginalFilename();
        originalFilename = id + "_" + originalFilename;
        try {
            img.transferTo(new File(realPath, originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Article article = new Article();
        article.setId(id);
        article.setImg(originalFilename);
        articleService.update(article);
    }

    @RequestMapping("selectOne")
    public Map<String, Object> selectOne(String uid, String id){
        Map<String, Object> map = new HashMap<>(2);
        Article article = articleService.selectOne(id);
        if (article != null) {
            map.put("status", 200);
            map.put("article", article);
        } else {
            map.put("status", -200);
            map.put("message", "未找到文章");
        }
        return map;
    }
}
