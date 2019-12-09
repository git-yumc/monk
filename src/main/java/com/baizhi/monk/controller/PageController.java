package com.baizhi.monk.controller;

import com.baizhi.monk.service.AlbumService;
import com.baizhi.monk.service.ArticleService;
import com.baizhi.monk.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumcw
 * @date 2019-12-04
 */
@RequestMapping("page")
@RestController
public class PageController {
    private final BannerService bannerService;
    private final AlbumService albumService;
    private final ArticleService articleService;

    public PageController(BannerService bannerService, AlbumService albumService, ArticleService articleService) {
        this.bannerService = bannerService;
        this.albumService = albumService;
        this.articleService = articleService;
    }

    @RequestMapping("one")
    public Map<String, Object> one(String uid, String type, String sub_type){
        Map<String, Object> result = new HashMap<>(16);
        result.put("status", 200);
        if ("all".equals(type)) {
            result.put("head", bannerService.select());
            result.put("albums", albumService.selectAll(1, 6).get("rows"));
            result.put("articles", articleService.selectAll(1, 6).get("rows"));
        }else if("wen".equals(type)){
            result.put("albums", albumService.selectAll(1, 10000).get("rows"));
        } else if ("si".equals(type)) {
            if ("xmfy".equals(sub_type)) {
                result.put("articles", articleService.selectAll(1, 10000).get("rows"));
            } else if ("ssyj".equals(sub_type)) {
                result.put("articles", articleService.select("7cb25f44-1662-11ea-af1d-000ec6d791da"));
            } else {
                result.put("status", -200);
                result.put("message", "请求错误");
            }
        }else {
            result.put("status", -200);
            result.put("message", "请求错误");
        }
        return result;
    }
}
