package com.baizhi.monk.controller;

import com.baizhi.monk.entity.Banner;
import com.baizhi.monk.service.BannerService;
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
 * @author yumc
 */
@RestController
@RequestMapping("banner")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @RequestMapping("select")
    public Map<String,Object> select(Integer page,Integer rows){
        Map<String, Object> map = bannerService.selectAll(page, rows);
        return map;
    }
@RequestMapping("edit")
    public Map<String,Object> edit(Banner banner,String oper) {
        Map<String,Object> map=new HashMap<>(16);
        if(oper.equals("add")){
            banner.setId(null);
            bannerService.insert(banner);
             map.put("id", banner.getId());
        }else if(oper.equals("edit")){
          bannerService.update(banner);
            map.put("id", banner.getId());
        }else if (oper.equals("del")){
            bannerService.delete(banner);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile src, String id, HttpSession session, HttpServletRequest request)  {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/statics/img/banner");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        // 防止重名操作
        String originalFilename = src.getOriginalFilename();
        originalFilename = id+"_"+originalFilename;
        try {
            src.transferTo(new File(realPath,originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Banner banner = new Banner();
        banner.setId(id);
        banner.setSrc(originalFilename);
        bannerService.update(banner);
    }
}
