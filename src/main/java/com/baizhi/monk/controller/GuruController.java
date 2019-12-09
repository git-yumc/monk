package com.baizhi.monk.controller;

import com.baizhi.monk.entity.Guru;
import com.baizhi.monk.service.GuruService;
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
@RequestMapping("guru")
public class GuruController {
    private final GuruService guruService;

    public GuruController(GuruService guruService) {
        this.guruService = guruService;
    }

    @RequestMapping("select")
    public Map<String, Object> select(Integer page, Integer rows) {
        Map<String, Object> map = guruService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(Guru guru, String oper) {
        Map<String, Object> map = new HashMap<>(16);
        if (oper.equals("add")) {
            guru.setId(null);
            guruService.insert(guru);
            map.put("id", guru.getId());
        } else if (oper.equals("edit")) {
            guruService.update(guru);
            map.put("id", guru.getId());
        } else if (oper.equals("del")) {
            guruService.delete(guru);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile img, String id, HttpSession session, HttpServletRequest request) {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/statics/img/guru");
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
        Guru guru = new Guru();
        guru.setId(id);
        guru.setImg(originalFilename);
        guruService.update(guru);
    }
}
