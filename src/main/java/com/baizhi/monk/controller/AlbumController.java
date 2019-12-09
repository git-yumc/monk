package com.baizhi.monk.controller;

import com.baizhi.monk.entity.Album;
import com.baizhi.monk.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("select")
    public Map<String, Object> select(Integer page, Integer rows) {
        Map<String, Object> map = albumService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(Album album, String oper) {
        Map<String, Object> map = new HashMap<>(16);
        if (oper.equals("add")) {
            album.setId(null);
            albumService.insert(album);
            map.put("id", album.getId());
        } else if (oper.equals("edit")) {
            albumService.update(album);
            map.put("id", album.getId());
        } else if (oper.equals("del")) {
            albumService.delete(album);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile img, String id, HttpSession session, HttpServletRequest request) {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/statics/img/album");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        // 防止重名操作
        String originalFilename = img.getOriginalFilename();
        originalFilename = id + "_" + originalFilename;
        try {
            img.transferTo(new File(realPath, originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Album album = new Album();
        album.setId(id);
        album.setImg(originalFilename);
        albumService.update(album);
    }

    @RequestMapping("selectOne")
    public Map<String, Object> selectOne(String uid, String id){
        Map<String, Object> map = new HashMap<>(4);
        Album album = albumService.selectOne(id);
        if (album != null) {
            map.put("status", 200);
            map.put("ablum", album);
        } else {
            map.put("status", -200);
            map.put("message", "未找到专辑");
        }
        return map;
    }
}
