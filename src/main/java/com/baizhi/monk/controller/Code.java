package com.baizhi.monk.controller;

import com.baizhi.monk.util.SecurityCode;
import com.baizhi.monk.util.SecurityImage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yumc
 */
@RequestMapping("code")
@RestController
public class Code {
    @RequestMapping("get")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = SecurityCode.getSecurityCode();
        req.getSession().setAttribute("imgCode", code);
        BufferedImage image = SecurityImage.createImage(code);
        ImageIO.write(image, "png", resp.getOutputStream());
    }
    @RequestMapping("sendCode")
    public Map<String, String> sendCode(String phone, HttpSession session){
        Map<String,String> result=new HashMap<>(2);
        String code = "1111";
        session.setAttribute("code", code);
        session.setAttribute("phone", phone);
        result.put("status", "200");
        result.put("message", "发送成功");
        return result;
    }
}
